package net.sda.myresumeapi.repositories;

import net.sda.myresumeapi.entities.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExperienceRepository extends MongoRepository<Experience, String> {}

