package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.*;

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

    private String companyMail;

    private String personalMail;

    private String avatar;

    private String country;

    private Province province;

    private District district;

    private Ward ward;

    private String address;

    private Status status;

    private String accountName;

    private SystemRole role;
}