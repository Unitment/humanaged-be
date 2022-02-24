package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.dto.ProjectDTO;

@Service
public interface IProjectService extends IService<ProjectDTO, String> {
    ProjectDTO updateProject(ProjectDTO projectDTO);
}