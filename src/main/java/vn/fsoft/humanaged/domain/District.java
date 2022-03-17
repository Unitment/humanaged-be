package vn.fsoft.humanaged.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class District {
    
    @Id
    private int id;

    @Nationalized
    private String name;

    @Nationalized
    private String prefix;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="province_id", insertable = true)
    // @JsonIgnore
    private Province province;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ward> wards;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private Set<Employee> employees;
}