package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.domain.ProjectRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDTO {
    private ProjectMemberKey projectMemberKey;
    private EmployeeDTO employee;
    private ProjectRole role;
    private Project project;
}