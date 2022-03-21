package vn.fsoft.humanaged.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Parameter;

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

    private String mail;

    private String avatar;

    private String phoneNumber;

    @Nationalized
    private String country;

    @Nationalized
    private String province;

    @Nationalized
    private String district;

    @Nationalized
    private String ward;

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
}