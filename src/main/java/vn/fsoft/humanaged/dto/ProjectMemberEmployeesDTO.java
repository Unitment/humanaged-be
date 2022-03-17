package vn.fsoft.humanaged.dto;

import lombok.Data;
import vn.fsoft.humanaged.domain.ProjectRole;

@Data
public class ProjectMemberEmployeesDTO {
    
    private EmployeeDTO employee;

    private ProjectRole role;
}
