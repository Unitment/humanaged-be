package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;

@Service
public interface IProjectMemberService extends IService<ProjectMember, ProjectMemberKey> {
    
}