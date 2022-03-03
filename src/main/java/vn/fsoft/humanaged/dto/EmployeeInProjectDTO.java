package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.ProjectRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInProjectDTO {
    private String id;
    private String name;
    private ProjectRole role;
}
