package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.domain.SystemRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFlatDTO {
    private String id;

    private String name;

    private String birthday;

    private Gender gender;

    private String phoneNumber;

    private String mail;

    private String country;

    private String province;

    private String district;

    private String ward;

    private String address;

    private Status status;

    private String accountName;

    private SystemRole role;
}