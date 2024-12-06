package net.sda.myresumeapi.repositories;

import net.sda.myresumeapi.entities.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {}

