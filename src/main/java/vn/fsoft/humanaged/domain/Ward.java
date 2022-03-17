package vn.fsoft.humanaged.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
public class Ward {
    
    @Id
    private int id;

    @Nationalized
    private String name;

    @Nationalized
    private String prefix;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("districtID")
    @JsonIgnore
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("provinceID")
    @JsonIgnore
    private Province province;

    @OneToMany(mappedBy = "ward")
    @JsonIgnore
    private Set<Employee> employees;
}
