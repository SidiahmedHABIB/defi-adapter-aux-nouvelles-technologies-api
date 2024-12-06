package net.sda.myresumeapi.repositories;

import net.sda.myresumeapi.entities.Education;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EducationRepository extends MongoRepository<Education, String> {}
