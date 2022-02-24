package vn.fsoft.humanaged.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GenericGenerator(
            name = "projectIdGen",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "P"),
            strategy = "vn.fsoft.humanaged.util.IdGenerator"
    )
    @GeneratedValue(generator = "projectIdGen")
    private String id;

    @Nationalized
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Nationalized
    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectState state;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<ProjectMember> projects;
}