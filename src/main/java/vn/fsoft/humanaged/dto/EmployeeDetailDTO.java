package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Status;

import java.time.LocalDate;
import java.util.Set;

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

    private String avatar;

    private String country;

    private String province;

    private String district;

    private String ward;

    private Status status;

    private Set<ProjectMemberProjectsDTO> projectMembers;
}