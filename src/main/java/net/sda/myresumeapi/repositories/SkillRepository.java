package net.sda.myresumeapi.repositories;

import net.sda.myresumeapi.entities.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, String> {}
