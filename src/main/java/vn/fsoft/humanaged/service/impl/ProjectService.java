package vn.fsoft.humanaged.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.dto.EmployeeInProjectDTO;
import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.repository.IProjectRepository;
import vn.fsoft.humanaged.service.IProjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<ProjectDTO> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectDTO> getById(String key) {
        Optional<Project> project = projectRepository.findById(key);
        project.orElseThrow(() -> new RuntimeException("Project not found"));
        return Optional.of(modelMapper.map(project.get(), ProjectDTO.class));
    }

    @Override
    public ProjectDTO save(ProjectDTO entity) {
        final Project newProject = projectRepository.save(modelMapper.map(entity, Project.class));
        
        List<EmployeeInProjectDTO> employees = entity.getEmployeeInProjectList();
        Set<ProjectMember> projectMembers = new HashSet<>();
        employees.stream().forEach(employee -> {
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
        employees.stream().forEach(employee -> {
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

    @Override
    public void deleteById(String key) {
        projectRepository.deleteById(key);
    }
}