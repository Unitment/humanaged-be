package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fsoft.humanaged.domain.Project;

public interface IProjectRepository extends JpaRepository<Project, String> {
}