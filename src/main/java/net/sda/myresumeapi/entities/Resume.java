package net.sda.myresumeapi.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "resumes")
public class Resume {
    @Id
    private String id;
    private String userId;
    private String fname;
    private String lname;
    private int score;
    private String request;
    private boolean portfolio;
    private Contact contact;
    private Profile profile;
    private List<Education> education;
    private List<Experience> experience;
    private List<Project> projects;
    private Skill skills;
    private List<String> languages;
    private List<String> interests;
}