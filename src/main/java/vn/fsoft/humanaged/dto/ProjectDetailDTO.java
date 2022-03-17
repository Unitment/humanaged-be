package vn.fsoft.humanaged.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;
import vn.fsoft.humanaged.domain.ProjectState;

@Data
public class ProjectDetailDTO {
    private String id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private ProjectState state;

    private Set<ProjectMemberEmployeesDTO> projectMembers;
}
