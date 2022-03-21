package vn.fsoft.humanaged.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.*;
import vn.fsoft.humanaged.dto.EmployeeInProjectDTO;
import vn.fsoft.humanaged.dto.ProjectDTO;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.repository.IProjectMemberRepository;
import vn.fsoft.humanaged.repository.IProjectRepository;
import vn.fsoft.humanaged.service.IProjectMemberService;
import vn.fsoft.humanaged.service.IProjectService;

import java.util.*;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getById(String key) {
        return projectRepository.findById(key);
    }

    @Override
    public Project save(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    public void deleteById(String key) {
//        Project project = projectRepository.findById(key)
//                .orElseThrow(() -> new RuntimeException("Project not found"));
//        projectRepository.deleteById(key);
//        project.getProjectMembers().stream().forEach(projectMember -> {
//            if (projectMember.getEmployee().getProjectMembers().isEmpty()
//                    || projectMember.getEmployee().getProjectMembers().stream().allMatch(arg0 -> arg0.getProject()
//                            .getState().equals(ProjectState.CLOSED))) {
//                projectMember.getEmployee().setStatus(Status.SUPPORT);
//                employeeRepository.save(projectMember.getEmployee());
//            }
//        });
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
            Employee member = employeeRepository.findByAccountName(employee.getAccountName())
                    .orElseThrow(() -> new RuntimeException(
                            "Employee " + "[ " + employee.getAccountName() + " ]" + " not found"));
            ProjectMember projectMember = new ProjectMember();
            if (member.getStatus() == null || member.getStatus().equals(Status.SUPPORT)) {
                member.setStatus(Status.WORKING);
                member = employeeRepository.save(member);
            }
            projectMember.setEmployee(member);
            projectMember.setProject(newProject);
            projectMember.setRole(employee.getRole());
            ProjectMemberKey id = new ProjectMemberKey(member.getId(), newProject.getId());
            projectMember.setId(id);
            projectMembers.add(projectMember);
        });
        newProject.setProjectMembers(projectMembers);

        entity = modelMapper.map(projectRepository.save(newProject), ProjectDTO.class);
        entity.setEmployeeInProjectList(employees);
        return entity;
    }

    @Override
    public Project updateProjectIsDelete(String id, boolean isDelete) {
        Optional<Project> oProject = this.projectRepository.findById(id);

        if (oProject.isPresent()) {
            Project project = oProject.get();
            project.setDelete(isDelete);
            List<Employee> members = new ArrayList<>();
            oProject.get().getProjectMembers().stream().forEach(projectMember -> {
                members.add(projectMember.getEmployee());
            });
            members.forEach(employee -> {
//                System.out.println(employee.getAccount().getAccountName()+": "+projectMemberService.findProjectAndMemberByPMId(employee.getId()).isEmpty());
//                System.out.println(employee.getAccount().getAccountName()+": "+ employee.getProjectMembers().stream().allMatch(arg0 -> arg0.getProject()
//                            .getState().equals(ProjectState.CLOSED))+", "+ employee.getProjectMembers().stream().allMatch(arg0 -> arg0.getProject()
//                        .isDelete()));
//                if (projectMemberService.findProjectAndMemberByPMId(employee.getId()).isEmpty()
                if (employee.getProjectMembers().stream().allMatch(projectMember -> projectMember.getProject().isDelete()==true)
                        ||employee.getProjectMembers().isEmpty())
                    employee.setStatus(Status.SUPPORT);
            });
            return this.projectRepository.save(project);
        } else return null;
    }

    @Override
    public List<Project> getAllByIsDeleteFalse() {
        return projectRepository.findAllByIsDeleteFalse();
    }


    @Override
    public ProjectDTO updateProject(ProjectDTO entity) {
        final Project project = projectRepository.findById(entity.getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<EmployeeInProjectDTO> employees = entity.getEmployeeInProjectList();
        Set<ProjectMember> projectMembers = new HashSet<>();
        employees.forEach(employee -> {
            Employee member = employeeRepository.findByAccountName(employee.getAccountName())
                    .orElseThrow(() -> new RuntimeException(
                            "Employee " + "[ " + employee.getAccountName() + " ]" + " not found"));
            ProjectMember projectMember = new ProjectMember();
            projectMember.setEmployee(member);
            projectMember.setProject(project);
            projectMember.setRole(employee.getRole());
            ProjectMemberKey id = new ProjectMemberKey(member.getId(), project.getId());
            projectMember.setId(id);
            projectMembers.add(projectMember);
        });
        project.getProjectMembers().stream().forEach(projectMember -> {
            if (!projectMembers.contains(projectMember)) {
                // projectRepository.deleteProjectMember(projectMember.getId());
                if (projectMember.getEmployee().getProjectMembers().size() == 1) {
                    projectMember.getEmployee().setStatus(Status.SUPPORT);
                    employeeRepository.save(projectMember.getEmployee());
                } else if (projectMember.getEmployee().getProjectMembers().stream()
                        .allMatch(arg0 -> !arg0.getProject().getId().equals(project.getId())
                                && arg0.getProject().getState().equals(ProjectState.CLOSED))) {
                    projectMember.getEmployee().setStatus(Status.SUPPORT);
                    employeeRepository.save(projectMember.getEmployee());
                }
            }
        });

        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setStartDate(entity.getStartDate());
        project.setEndDate(entity.getEndDate());
        project.setState(entity.getState());

        if (project.getState() == ProjectState.CLOSED) {
            projectMembers.stream().forEach(projectMember -> {
                if (projectMember.getEmployee().getProjectMembers().stream()
                        .allMatch(arg0 -> arg0.getProject().getState().equals(ProjectState.CLOSED))) {
                    projectMember.getEmployee().setStatus(Status.SUPPORT);
                    projectMember.setEmployee(employeeRepository.save(projectMember.getEmployee()));
                }
            });
        } else {
            projectMembers.stream().forEach(projectMember -> {
                if (projectMember.getEmployee().getStatus().equals(Status.SUPPORT)) {
                    projectMember.getEmployee().setStatus(Status.WORKING);
                    projectMember.setEmployee(employeeRepository.save(projectMember.getEmployee()));
                }
            });
        }
        project.getProjectMembers().clear();
        project.getProjectMembers().addAll(projectMembers);
        entity = modelMapper.map(projectRepository.save(project), ProjectDTO.class);
        entity.setEmployeeInProjectList(employees);
        return entity;
    }

}