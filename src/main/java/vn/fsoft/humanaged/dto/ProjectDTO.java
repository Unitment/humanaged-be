package vn.fsoft.humanaged.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectState;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private ProjectState state; // enum
    private List<EmployeeInProjectDTO> EmployeeInProjectList;
    private Set<ProjectMember> projectMembers;
}