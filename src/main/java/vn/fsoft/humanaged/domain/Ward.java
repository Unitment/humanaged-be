package vn.fsoft.humanaged.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Set;

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
    @JoinColumn(name = "district_id", insertable = true)
    @JsonIgnore
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", insertable = true)
    @JsonIgnore
    private Province province;

    @OneToMany(mappedBy = "ward")
    @JsonIgnore
    private Set<Employee> employees;
}