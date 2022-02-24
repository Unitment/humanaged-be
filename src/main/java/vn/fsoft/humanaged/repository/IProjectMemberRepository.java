package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;

@Repository
public interface IProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberKey> {

}