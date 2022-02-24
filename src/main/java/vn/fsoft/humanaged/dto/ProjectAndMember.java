package vn.fsoft.humanaged.dto;

import lombok.*;
import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectMember;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAndMember implements Serializable {
    private Project project;

    // private Employee[] memberList;
    private List<ProjectMember> memberList;
}