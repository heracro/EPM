package hihi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Integer projectId;
    private String title;
    private String body;
    private LocalDate plannedStart;
    private LocalDate plannedEnd;
    private LocalDate realStart;
    private LocalDate realEnd;
    private Integer workingHours;
    private Cause cause;
    private LocalDate materialsReady;
    private String docsLocation;
    private String status;
}
