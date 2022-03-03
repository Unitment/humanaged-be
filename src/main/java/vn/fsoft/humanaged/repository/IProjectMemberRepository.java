package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.domain.ProjectRole;

import java.util.List;

public interface IProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberKey> {

    List<ProjectMember> findAllByRole(ProjectRole role);

    List<ProjectMember> findAllByEmployee_Id(String id);

    List<ProjectMember> findAllByProject_Id(String id);

    List<ProjectMember> findAllByEmployee_IdOrderByRole(String employeeId);

    List<ProjectMember> findAllByProject_IdOrderByRole(String id);

    @Query(value = "select p from ProjectMember as p where p.project.id = ?1 and p.role = 'LEADER'")
    List<ProjectMember> isProjectHasLeader(String projectID);
}