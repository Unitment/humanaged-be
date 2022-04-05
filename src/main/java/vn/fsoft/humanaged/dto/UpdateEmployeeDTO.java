package vn.fsoft.humanaged.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.District;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Province;
import vn.fsoft.humanaged.domain.Ward;

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

    private String mail;

    private String avatar;

    private Province province;

    private District district;

    private Ward ward;

    private String address;
}
