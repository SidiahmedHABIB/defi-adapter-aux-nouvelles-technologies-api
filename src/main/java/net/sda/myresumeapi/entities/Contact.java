package net.sda.myresumeapi.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "contacts")
public class Contact {
    @Id
    private String id;
    private String email;
    private String phone;
    private String location;
    private String linkedin;
    private String github;
}
