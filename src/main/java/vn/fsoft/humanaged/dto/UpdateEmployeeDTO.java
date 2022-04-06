package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.District;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Province;
import vn.fsoft.humanaged.domain.Ward;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeDTO {
    private String id;

    private String name;

    private LocalDate birthday;

    private Gender gender;

    private String phoneNumber;

    private String companyMail;

    private String personalMail;

    private String avatar;

    private Province province;

    private District district;

    private Ward ward;

    private String address;
}