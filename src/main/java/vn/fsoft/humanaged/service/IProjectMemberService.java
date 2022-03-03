package vn.fsoft.humanaged.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.fsoft.humanaged.dto.ProjectAndMember;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.domain.ProjectRole;

@Service
public interface IProjectMemberService extends IService<ProjectMember, ProjectMemberKey> {

    List<ProjectMember> findMemberByRole(ProjectRole role);

    List<ProjectMember> findMemberByEmployeeId(String employeeId);

    List<ProjectAndMember> findProjectAndMemberByPMId(String employeeId);

    List<ProjectMember> findMemberByProjectId(String id);

}