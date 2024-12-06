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
@Document(collection = "profiles")
public class Profile {
    @Id
    private String id;
    private String title;
    private String description;
}