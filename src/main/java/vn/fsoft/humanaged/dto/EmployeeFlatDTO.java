package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.District;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Province;
import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.domain.SystemRole;
import vn.fsoft.humanaged.domain.Ward;

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