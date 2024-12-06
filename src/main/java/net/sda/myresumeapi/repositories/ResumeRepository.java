package net.sda.myresumeapi.repositories;

import net.sda.myresumeapi.entities.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends MongoRepository<Resume, String> {
    List<Resume> findByUserIdAndPortfolio(String user, boolean isProtfolio);

}
