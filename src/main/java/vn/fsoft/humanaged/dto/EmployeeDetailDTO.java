package vn.fsoft.humanaged.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Status;

@Data
@NoArgsConstructor
public class EmployeeDetailDTO {
    private String id;

    private String name;

    private LocalDate birthday;

    private Gender gender;

    private String mail;

    private String country;

    private String province;

    private String district;

    private String ward;

    private Status status;

    private Set<ProjectMemberProjectsDTO> projects;
}
