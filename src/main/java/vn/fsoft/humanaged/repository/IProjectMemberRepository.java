package vn.fsoft.humanaged.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.domain.ProjectRole;

public interface IProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberKey> {

    List<ProjectMember> findAllByRole(ProjectRole role);

    List<ProjectMember> findAllByEmployee_Id(String id);

    List<ProjectMember> findAllByProject_Id(String id);

    List<ProjectMember> findAllByEmployee_IdOrderByRole(String employeeId);
}