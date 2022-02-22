package vn.fsoft.humanaged.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {

    @EmbeddedId
    private ProjectMemberKey id;
    
    @ManyToOne
    @MapsId("employeeID")
    private Employee employee;

    @ManyToOne
    @MapsId("projectID")
    private Project project;

    @Enumerated(EnumType.STRING)
    private ProjectRole role;
}