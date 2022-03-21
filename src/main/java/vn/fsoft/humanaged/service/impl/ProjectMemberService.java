package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fsoft.humanaged.domain.*;
import vn.fsoft.humanaged.dto.MemberDTO;
import vn.fsoft.humanaged.dto.ProjectAndMember;
import vn.fsoft.humanaged.repository.IProjectMemberRepository;
import vn.fsoft.humanaged.service.IEmployeeService;
import vn.fsoft.humanaged.service.IProjectMemberService;
import vn.fsoft.humanaged.service.IProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectMemberService implements IProjectMemberService {

    @Autowired
    private IProjectMemberRepository projectMemberRepository;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public List<ProjectMember> getAll() {
        return null;
    }

    @Override
    public Optional<ProjectMember> getById(ProjectMemberKey key) {
        return Optional.empty();
    }

    @Override
    public ProjectMember save(ProjectMember entity) {
        return projectMemberRepository.save(entity);
    }

    @Override
    public void deleteById(ProjectMemberKey key) {
    }

    @Override
    public List<ProjectMember> findMemberByRole(ProjectRole role) {
        List<ProjectMember> ls =  projectMemberRepository.findAllByRole(role);
        List<ProjectMember> returnValue = new ArrayList<>();
        for (int i = 0; i<ls.size();i++){
            if (ls.get(i).getProject().isDelete()==false) returnValue.add(ls.get(i));
        }
        return returnValue;
    }

    @Override
    public List<ProjectMember> findMemberByEmployeeId(String employeeId) {
        List<ProjectMember> temp1 = projectMemberRepository.findAllByEmployee_Id(employeeId);
        List<ProjectMember> temp2 = projectMemberRepository.findAllByRole(ProjectRole.LEADER);
        List<ProjectMember> temp3 = projectMemberRepository.findAllByRole(ProjectRole.MEMBER);
        // temp2.removeAll(temp1);
        temp1.removeAll(temp2);
        temp1.removeAll(temp3);
        return temp1;
        // return projectMemberRepository.findAllByEmployee_Id(employeeId);
    }

    @Override
    public List<ProjectMember> findMemberByProjectId(String id) {
        List<ProjectMember> temp1 = projectMemberRepository.findAllByProject_IdOrderByRole(id);
        List<ProjectMember> temp2 = projectMemberRepository.findAllByRole(ProjectRole.PM);
        temp1.removeAll(temp2);
        return temp1;
    }

    @Override
    public void addEmployeeToProject(MemberDTO memberDTO) {

        Optional<Project> prj = projectService.getById(memberDTO.getProjectID());

        for (String empID : memberDTO.getEmployeeIDList()) {
            Optional<Employee> emp = employeeService.getById(empID);
            emp.ifPresent(employee -> {
                if (employee.getStatus() == Status.SUPPORT) {
                    employee.setStatus(Status.WORKING);
                    employeeService.save(employee);
                }
            });
            ProjectMember projectMember = new ProjectMember(new ProjectMemberKey(empID, memberDTO.getProjectID()),
                    emp.get(), prj.get(), memberDTO.getRole());

            projectMemberRepository.save(projectMember);
        }
    }

    @Override
    public boolean isProjectHasLeader(String projectID) {
        return !projectMemberRepository.isProjectHasLeader(projectID).isEmpty();
    }

    @Override
    public List<ProjectAndMember> findProjectAndMemberByPMId(String employeeId) {
        List<ProjectMember> temp1 = findMemberByEmployeeId(employeeId);
        List<ProjectAndMember> temp2 = new ArrayList<>();
        for (ProjectMember projectMember : temp1) {
            if (projectMember.getProject().isDelete()==false)
            temp2.add(new ProjectAndMember(projectMember.getProject(), findMemberByProjectId(projectMember.getId().getProjectID())));
        }

        return temp2;
    }

    @Override
    @Transactional
    public boolean deleteEmployeeFromProject(String employeeId, String projectId) {
        boolean isRemove = this.projectMemberRepository.removeEmployeeFromProject(employeeId, projectId) > 0;

        List<ProjectMember> projectMembers = this.projectMemberRepository.findAllByEmployee_Id(employeeId);
        
        if(projectMembers.stream().allMatch(pm -> pm.getProject().getState().equals(ProjectState.CLOSED))){
            this.employeeService.getById(employeeId).ifPresent(e -> {
                e.setStatus(Status.SUPPORT);
                this.employeeService.save(e);
            });
        }

        return isRemove;
    }
}