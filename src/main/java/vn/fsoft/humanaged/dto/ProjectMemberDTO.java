package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Project;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectMemberDTO {
    private Employee employee;

    private Project project;
}
