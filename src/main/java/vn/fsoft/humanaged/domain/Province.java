package vn.fsoft.humanaged.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    
    @Id
    private int id;

    @Nationalized
    private String name;

    private String code;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    // @JsonIgnore
    private Set<District> districts;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ward> wards;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private Set<Employee> employees;
}