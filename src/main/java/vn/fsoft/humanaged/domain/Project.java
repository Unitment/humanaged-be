package vn.fsoft.humanaged.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectState state;

    @OneToMany(mappedBy = "project")
    private Set<ProjectMember> projects;
}