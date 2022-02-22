package vn.fsoft.humanaged.service;

import java.util.List;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectState;

@Service
public interface IProjectService extends IService<Project, String> {

    List<Project> findProjectByState(ProjectState state);
}