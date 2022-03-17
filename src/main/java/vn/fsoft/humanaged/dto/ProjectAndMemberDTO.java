package vn.fsoft.humanaged.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAndMemberDTO {
    private ProjectDTO project;

    private List<ProjectMemberDTO> memberList;
}