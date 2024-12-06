package net.sda.myresumeapi.controllers;

import net.sda.myresumeapi.entities.Resume;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class chatController {

    private ChatClient chatClient;

    public chatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    @GetMapping(value = "/hello/{hello}")
    public ChatClient.CallResponseSpec hello(@PathVariable("hello") String hello) {
        return chatClient.prompt()
                .system("return story")  // Simplified for demonstration
                .user(hello)
                .call();  // Assuming `.call()` returns a String directly
    }


    @GetMapping(value = "/chat/{test}")
    public  Resume chat(@PathVariable("test") String test){
        System.out.println("Received question: " + test);

        return chatClient.prompt()
                .system("""
                i want you generate resume for this job description taken the resume data of user 
                and return result following this json format:
                        {
                            "userId": "672bf5b9fd2e1512bb0b2bdc",
                              fname: Sidi Ahmed,
                              lname: Habib,
                              contact: {
                                email: sidiahmedhabib@gmail.com,
                                phone: 34136507,
                                address: sousse, tunis,
                              },
                              profile: {
                                title: 'Software Engineer',
                                description:
                                  'Designing and developing high-volume, low-latency applications for mission-critical systems. Contributing in all phases of the development lifecycle.',
                              },
                              education: [{
                                degree: 'Bachelor of Science',
                                major: 'Computer Science',
                                university: 'Your University',
                                location: 'Your City, Your Country',
                                relevantCourses: [
                                  'Java 8+',
                                  'JEE 7+',
                                  'Spring Framework',
                                  'Microservices Architecture',
                                  'CI/CD Environment',
                                  'Apache Kafka',
                                ],
                                completionYear: 2024,
                              }],
                              experience: [{
                                title: 'Software Engineer',
                                company: 'ABC Solutions',
                                location: 'Your City, Your Country',
                                start_date: '2024-05-22',
                                end_date: 'Present',
                                description:
                                  'Designing and developing high-volume, low-latency applications for mission-critical systems. Contributing in all phases of the development lifecycle.',
                                skills: [
                                  'Java 8+',
                                  'JEE 7+',
                                  'Spring Framework',
                                  'Microservices Architecture',
                                  'CI/CD Environment',
                                  'Apache Kafka',
                                ],
                              }],
                              projects: [{
                                title: 'Software Engineer',
                                description:
                                  'Designing and developing high-volume, low-latency applications for mission-critical systems. Contributing in all phases of the development lifecycle.',
                                technologies: [
                                  'Java 8+',
                                  'JEE 7+',
                                  'Spring Framework',
                                  'Microservices Architecture',
                                  'CI/CD Environment',
                                  'Apache Kafka',
                                ],
                              }],
                              skills: {
                                technical: [
                                  'Java 8+',
                                  'JEE 7+',
                                  'Spring Framework',
                                  'Microservices Architecture',
                                  'CI/CD Environment',
                                  'Apache Kafka',
                                ],
                                tools: ['Docker', 'Kubernetes'],
                                others: [
                                  'Object-Oriented Design',
                                  'Design Patterns',
                                  'Unit and Integration Testing',
                                  'Database Knowledge',
                                  'Cloud Architectures',
                                  'Troubleshooting',
                                ],
                              },
                              languages: ['Arabic','French'],
                              interests: ["Gaming", "Technology", "Machine Learning"],
                            }
                """)
                .user(test)
                .call()
                .entity(Resume.class);
    }

}
