package net.sda.myresumeapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.sda.myresumeapi.dto.GenerateResumeRequest;
import net.sda.myresumeapi.entities.Resume;
import net.sda.myresumeapi.entities.User;
import net.sda.myresumeapi.services.ResumeService;
import net.sda.myresumeapi.services.UserService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resumes")
@CrossOrigin(origins = "http://localhost:4200")
public class ResumeController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final ChatClient chatClient;

    public ResumeController(ResumeService resumeService, UserService userService, ChatClient.Builder builder) {
        this.resumeService = resumeService;
        this.userService = userService;
        this.chatClient = builder.build();
    }

    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeService.getAllResumes();

        if (resumes == null || resumes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resumes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable("id") String id) {
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get-all-user-resumes/{userId}")
    public ResponseEntity<List<Resume>> getAllUserResumes(@PathVariable("userId") String userId) {
        List<Resume> resumes = resumeService.getAllUserResumes(userId);

        if (resumes == null || resumes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(resumes);
        }
    }


    @PostMapping("/generate")
    public ResponseEntity<Resume> generateResume(@RequestBody GenerateResumeRequest generateResumeRequest) {
        Optional<Resume> portfolioOpt = resumeService.getUserPortfolio(generateResumeRequest.userId());
        if (portfolioOpt.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Resume portfolio = portfolioOpt.get();

        // Convert the portfolio object to a JSON string
        String portfolioJson;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            portfolioJson = objectMapper.writeValueAsString(portfolio);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Prepare and send the prompt to OpenAI API
        Resume generatedResume = chatClient.prompt()
                .system(String.format("""
    You are a highly skilled resume writer and career consultant. The following is the candidate's existing resume data:

    %s

    Please craft a resume that appears human-created and professional, tailored for the specific job description below. The resume should feel authentic, highlighting the candidate's most relevant skills, experiences, accomplishments, and education in a way that naturally aligns with the job.

    Ensure that:
    - The language flows naturally, as if written by a human.
    - The profile summary captures the essence of the candidate and aligns with the job requirements.
    - Key skills are showcased thoughtfully and connected directly to the job.
    - Relevant experiences and projects are described with clarity and impact, focusing on achievements and contributions.
    - The format is clean, visually appealing, and ready for recruiters to review.

    Job Description: %s
    """, portfolioJson, generateResumeRequest.jobDescription()))
                .user("Generate a resume that aligns with the job description.")
                .call()
                .entity(Resume.class);
        generatedResume.setRequest(generateResumeRequest.jobDescription());
        return ResponseEntity.ok(generatedResume);
    }

    @PostMapping
    public Resume createResume(@RequestBody Resume resume) {
        resume.setId(null);
        resume.setPortfolio(false);
        return resumeService.createResume(resume);
    }

    @PostMapping("/create-user-portfolio")
    public ResponseEntity<Resume> createUserPortfolio(@RequestBody Resume resume) {
        try {
            User user = userService.getUserById(resume.getUserId());

            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            user.setScore(1);
            resume.setScore(1);
            resume.setPortfolio(true);
            userService.updateUser(user);
            Resume createdResume = resumeService.createResume(resume);

            return ResponseEntity.ok(createdResume);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Resume> updateResume(@PathVariable("id") String id, @RequestBody Resume resume) {
        try {
            return ResponseEntity.ok(resumeService.updateResume(id, resume));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable("id") String id) {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }
}