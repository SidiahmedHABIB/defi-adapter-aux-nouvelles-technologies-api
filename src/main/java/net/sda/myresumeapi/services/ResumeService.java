package net.sda.myresumeapi.services;


import net.sda.myresumeapi.entities.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    List<Resume> getAllResumes();
    Optional<Resume> getResumeById(String id);
    Optional<Resume> getUserPortfolio(String id);
    List<Resume> getAllUserResumes(String id);
    Resume createResume(Resume resume);
    Resume updateResume(String id, Resume resume);
    void deleteResume(String id);
}

