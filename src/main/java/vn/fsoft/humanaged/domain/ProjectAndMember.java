package vn.fsoft.humanaged.domain;

import lombok.*;


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