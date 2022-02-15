package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.repository.IProjectMemberRepository;
import vn.fsoft.humanaged.service.IProjectMemberService;

import java.util.List;
import java.util.Optional;

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
}