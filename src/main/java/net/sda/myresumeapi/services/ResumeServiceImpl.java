package net.sda.myresumeapi.services;

import net.sda.myresumeapi.entities.Resume;
import net.sda.myresumeapi.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    @Override
    public Optional<Resume> getResumeById(String id) {
        return resumeRepository.findById(id);
    }

    @Override
    public Optional<Resume> getUserPortfolio(String id) {
        return resumeRepository.findByUserIdAndPortfolio(id,true).stream().findFirst();
    }

    @Override
    public List<Resume> getAllUserResumes(String id) {
        return resumeRepository.findByUserIdAndPortfolio(id,false);
    }

    @Override
    public Resume createResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public Resume updateResume(String id, Resume resume) {
        if (resumeRepository.existsById(id)) {
            resume.setId(id);
            return resumeRepository.save(resume);
        } else {
            throw new RuntimeException("Resume not found");
        }
    }

    @Override
    public void deleteResume(String id) {
        resumeRepository.deleteById(id);
    }
}
