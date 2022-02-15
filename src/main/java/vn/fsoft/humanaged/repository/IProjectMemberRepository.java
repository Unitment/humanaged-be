package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;

public interface IProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberKey> {
}