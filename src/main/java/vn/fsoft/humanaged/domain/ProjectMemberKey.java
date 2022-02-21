package vn.fsoft.humanaged.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberKey implements Serializable {

    @Column(name = "employeeId")
    String employeeID;

    @Column(name = "projectId")
    String projectID;
}