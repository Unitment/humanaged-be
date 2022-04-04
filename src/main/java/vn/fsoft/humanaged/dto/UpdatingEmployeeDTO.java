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
public class UpdatingEmployeeDTO {
    private String id;

    private LocalDate birthday;

    private Gender gender;

    private String phoneNumber;

    private String avatar;

    private Province province;

    private District district;

    private Ward ward;

    private String address;

    public void updateEmployee(Employee employee){
        if(!employee.getId().equals(this.getId())) throw new IllegalArgumentException("id mismatch");
        
        employee.setBirthday(this.birthday);
        employee.setGender(this.gender);
        employee.setPhoneNumber(this.phoneNumber);
        employee.setAvatar(this.avatar);
        employee.setProvince(this.province);
        employee.setDistrict(this.district);
        employee.setWard(this.ward);
        employee.setAddress(this.address);
    }
}
