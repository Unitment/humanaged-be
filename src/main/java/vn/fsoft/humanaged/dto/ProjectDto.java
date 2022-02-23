package vn.fsoft.humanaged.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.ProjectState;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private String id;

    private String name;

    private String description;

    private ProjectState state;

    private LocalDate startDate;

}
