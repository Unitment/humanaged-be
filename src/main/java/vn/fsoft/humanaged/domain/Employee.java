package vn.fsoft.humanaged.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Parameter;
import vn.fsoft.humanaged.dto.UpdateEmployeeDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GenericGenerator(
            name = "employeeIdGen",
            parameters = @Parameter(name = "prefix", value = "E"),
            strategy = "vn.fsoft.humanaged.util.IdGenerator"
    )
    @GeneratedValue(generator = "employeeIdGen")
    private String id;

    @Nationalized
    private String name;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String companyMail;

    private String personalMail;

    @Column(length = 1000)
    private String avatar;

    private String phoneNumber;

    @Nationalized
    private String country;

    @ManyToOne
    @JoinColumn
    private Province province;

    @ManyToOne
    @JoinColumn
    private District district;

    @ManyToOne
    @JoinColumn
    private Ward ward;

    @Nationalized
    private String address;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false, columnDefinition = "bit default 0")
    private boolean isDelete;

    private LocalDateTime modifiedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountName")
    private Account account;

    @OneToOne(mappedBy = "bul")
    @JsonIgnore
    private BusinessUnit businessUnit;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<ProjectMember> projectMembers;

    public Employee(String id, String name, LocalDate birthday, Gender gender, String companyMail, String personalMail, String avatar, String phoneNumber, String country, Province province, District district, Ward ward, String address, Status status, boolean isDelete, LocalDateTime modifiedDate, Account account) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.companyMail = companyMail;
        this.personalMail = personalMail;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.status = status;
        this.isDelete = isDelete;
        this.modifiedDate = modifiedDate;
        this.account = account;
    }

    public Employee update(UpdateEmployeeDTO uEmployee) {
        if (!uEmployee.getId().equals(this.getId())) throw new IllegalArgumentException("id mismatch");

        this.setBirthday(uEmployee.getBirthday());
        this.setGender(uEmployee.getGender());
        this.setPhoneNumber(uEmployee.getPhoneNumber());
        this.setAvatar(uEmployee.getAvatar());
        this.setCompanyMail(uEmployee.getCompanyMail());
        this.setPersonalMail(uEmployee.getPersonalMail());
        this.setProvince(uEmployee.getProvince());
        this.setDistrict(uEmployee.getDistrict());
        this.setWard(uEmployee.getWard());
        this.setAddress(uEmployee.getAddress());

        return this;
    }
}