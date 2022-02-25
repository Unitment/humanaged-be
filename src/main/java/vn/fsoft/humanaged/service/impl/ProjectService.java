package vn.fsoft.humanaged.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.*;
import vn.fsoft.humanaged.dto.EmployeeInProjectDTO;
import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.repository.IProjectRepository;
import vn.fsoft.humanaged.service.IProjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getById(String key) {
        return Optional.empty();
    }

    @Override
    public Project save(Project entity) {
        return null;
    }

    @Override
    public void deleteById(String key) {

    }

    @Override
    public List<Project> findProjectByState(ProjectState state) {
        return projectRepository.findAllByState(state);
    }

    @Override
    public ProjectDTO saveDTO(ProjectDTO entity) {
        final Project newProject = projectRepository.save(modelMapper.map(entity, Project.class));

        List<EmployeeInProjectDTO> employees = entity.getEmployeeInProjectList();
        Set<ProjectMember> projectMembers = new HashSet<>();
        employees.forEach(employee -> {
            Employee member = employeeRepository.findById(employee.getId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            employee.setName(member.getName());
            ProjectMember projectMember = new ProjectMember();
            projectMember.setEmployee(member);
            projectMember.setProject(newProject);
            projectMember.setRole(employee.getRole());
            ProjectMemberKey id = new ProjectMemberKey(employee.getId(), newProject.getId());
            projectMember.setId(id);
            // projectRepositoryMember.save(projectMember);
            projectMembers.add(projectMember);
        });
        newProject.setProjectMembers(projectMembers);

        entity = modelMapper.map(projectRepository.save(newProject), ProjectDTO.class);
        entity.setEmployeeInProjectList(employees);
        return entity;
    }

    @Override
    public ProjectDTO updateProject(ProjectDTO entity) {
        final Project project = projectRepository.findById(entity.getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<EmployeeInProjectDTO> employees = entity.getEmployeeInProjectList();
        Set<ProjectMember> projectMembers = new HashSet<>();
        employees.forEach(employee -> {
            Employee member = employeeRepository.findById(employee.getId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            employee.setName(member.getName());
            ProjectMember projectMember = new ProjectMember();
            projectMember.setEmployee(member);
            projectMember.setProject(project);
            projectMember.setRole(employee.getRole());
            ProjectMemberKey id = new ProjectMemberKey(employee.getId(), project.getId());
            projectMember.setId(id);
            projectMembers.add(projectMember);
        });
        project.getProjectMembers().clear();
        project.getProjectMembers().addAll(projectMembers);
        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setStartDate(entity.getStartDate());
        project.setEndDate(entity.getEndDate());
        project.setState(entity.getState());

        entity = modelMapper.map(projectRepository.save(project), ProjectDTO.class);
        entity.setEmployeeInProjectList(employees);
        return entity;
    }

}