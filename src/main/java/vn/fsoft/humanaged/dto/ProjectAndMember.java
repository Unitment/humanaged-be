package vn.fsoft.humanaged.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectMember;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAndMember implements Serializable {
    private Project project;

    // private Employee[] memberList;
    private List<ProjectMember> memberList;
}