package se.BTH.ITProjectManagement.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Document(collection = "sprint")
public class Sprint {
    @Id
    private String id;
    private String name;
    private String goal;
    private LocalDate delivery;
    private LocalDate retrospective;
    private LocalDate demo;
    private DayOfWeek review;
    private LocalTime daily_meeting;

}
