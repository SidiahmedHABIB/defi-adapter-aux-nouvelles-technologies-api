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
@Document(collection = "educations")
public class Education {
    @Id
    private String id;
    private String degree;
    private String major;
    private String university;
    private String location;
    private Date startDate;
    private Date endDate;
    private List<String> relevantCourses;
}
