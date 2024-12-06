package net.sda.myresumeapi.repositories;

import net.sda.myresumeapi.entities.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {}