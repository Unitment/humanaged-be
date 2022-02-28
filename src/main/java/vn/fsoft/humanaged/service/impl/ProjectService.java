package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.repository.IProjectRepository;
import vn.fsoft.humanaged.service.IProjectService;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

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
        return null;
    }

    @Override
    public void deleteById(String key) {

    }
}