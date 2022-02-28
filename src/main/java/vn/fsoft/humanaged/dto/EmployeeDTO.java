package vn.fsoft.humanaged.dto;

import java.time.LocalDate;

import lombok.Data;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Status;

@Data
public class EmployeeDTO {
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
}
