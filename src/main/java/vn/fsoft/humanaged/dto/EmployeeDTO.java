package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.Account;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String id;

    private String name;

    private LocalDate birthday;

    private Gender gender;

    private String phoneNumber;

    private String mail;

    private String country;

    private String province;

    private String district;

    private String ward;

    private String address;

    private Status status;

    private boolean isDelete;

    private Account account;

    public EmployeeDTO(EmployeeFlatDTO employeeFlatDTO) {
        this.id = employeeFlatDTO.getId();
        this.name = employeeFlatDTO.getName();

        this.birthday = LocalDate.parse(employeeFlatDTO.getBirthday(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.gender = employeeFlatDTO.getGender();
        this.phoneNumber = employeeFlatDTO.getPhoneNumber();
        this.mail = employeeFlatDTO.getMail();
        this.country = employeeFlatDTO.getCountry();
        this.province = employeeFlatDTO.getProvince();
        this.district = employeeFlatDTO.getDistrict();
        this.ward = employeeFlatDTO.getWard();
        this.address = employeeFlatDTO.getAddress();
        this.status = employeeFlatDTO.getStatus();
        this.account = new Account(
                employeeFlatDTO.getAccountName(),
                employeeFlatDTO.getAccountName(),
                employeeFlatDTO.getRole()
        );
    }
}
