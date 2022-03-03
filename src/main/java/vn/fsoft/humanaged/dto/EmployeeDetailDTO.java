package vn.fsoft.humanaged.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private Set<ProjectMemberProjectsDTO> projectMember;
}
