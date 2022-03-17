package vn.fsoft.humanaged.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectState;

@Repository
public interface IProjectRepository extends JpaRepository<Project, String> {

    List<Project> findAllByState(ProjectState state);
}