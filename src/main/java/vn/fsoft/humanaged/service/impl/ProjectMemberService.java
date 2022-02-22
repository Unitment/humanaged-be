package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fsoft.humanaged.domain.ProjectAndMember;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.domain.ProjectRole;
import vn.fsoft.humanaged.repository.IProjectMemberRepository;
import vn.fsoft.humanaged.service.IProjectMemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectMemberService implements IProjectMemberService {

    @Autowired
    private IProjectMemberRepository projectMemberRepository;

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
        return null;
    }

    @Override
    public void deleteById(ProjectMemberKey key) {

    }

    @Override
    public List<ProjectMember> findMemberByRole(ProjectRole role) {
        return projectMemberRepository.findAllByRole(role);
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
        List<ProjectMember> temp1 = projectMemberRepository.findAllByProject_Id(id);
        List<ProjectMember> temp2 = projectMemberRepository.findAllByRole(ProjectRole.PM);
        temp1.removeAll(temp2);
        return temp1;
    }

    @Override
    public List<ProjectAndMember> findProjectAndMemberByPMId(String employeeId) {
        List<ProjectMember> temp1 = findMemberByEmployeeId(employeeId);
        List<ProjectAndMember> temp2 = new ArrayList<>();
        for (ProjectMember projectMember : temp1) {
            temp2.add(new ProjectAndMember(projectMember.getProject(), findMemberByProjectId(projectMember.getId().getProjectID())));
        }
        
        return temp2;
    }
}