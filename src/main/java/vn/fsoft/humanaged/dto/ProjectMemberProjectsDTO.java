package vn.fsoft.humanaged.dto;

import lombok.Data;
import vn.fsoft.humanaged.domain.ProjectRole;

@Data
public class ProjectMemberProjectsDTO {

    private ProjectDTO project;

    private ProjectRole role;
}
