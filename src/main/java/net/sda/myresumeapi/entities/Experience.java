package net.sda.myresumeapi.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "experiences")
public class Experience {
    @Id
    private String id;
    private String title;
    private String company;
    private String location;
    private Date startDate;
    private Date endDate;
    private String description;
    private List<String> skills;
    // Getters and Setters
}