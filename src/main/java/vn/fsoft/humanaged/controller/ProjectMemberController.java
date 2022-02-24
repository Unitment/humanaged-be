package vn.fsoft.humanaged.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.fsoft.humanaged.domain.ProjectAndMember;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectRole;
import vn.fsoft.humanaged.dto.ProjectAndMemberDto;
import vn.fsoft.humanaged.dto.ProjectMemberDto;
import vn.fsoft.humanaged.service.IProjectMemberService;

@RestController
@RequestMapping("/api/projectMember")
public class ProjectMemberController {

    @Autowired
    private IProjectMemberService projectMemberService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("role/{role}")
    public ResponseEntity<List<ProjectMemberDto>> getMemberByRole(@PathVariable("role") ProjectRole role){
        List<ProjectMember> projectMember = projectMemberService.findMemberByRole(role);
        List<ProjectMemberDto> subProjectMems = projectMember.stream()
                        .map(projectMem -> modelMapper.map(projectMem, ProjectMemberDto.class))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(subProjectMems,HttpStatus.OK);
    }

    //Lấy ra pm có id tương ứng
    @GetMapping("pm/{employeeId}")
    public ResponseEntity<List<ProjectMemberDto>> getMemberByRole(@PathVariable("employeeId") String employeeId){
        List<ProjectMember> projectMember = projectMemberService.findMemberByEmployeeId(employeeId);
        List<ProjectMemberDto> subProjectMems = projectMember.stream()
                        .map(projectMem -> modelMapper.map(projectMem, ProjectMemberDto.class))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(subProjectMems,HttpStatus.OK);
    }

    // @GetMapping("projectId/{id}")
    // public ResponseEntity<List<ProjectMemberDto>> getMemberByProjectId(@PathVariable("id") String id){
    //     List<ProjectMember> projectMember = projectMemberService.findMemberByEmployeeId(id);
    //     List<ProjectMemberDto> subProjectMems = projectMember.stream()
    //                     .map(projectMem -> modelMapper.map(projectMem, ProjectMemberDto.class))
    //                     .collect(Collectors.toList());
    //     return new ResponseEntity<>(subProjectMems,HttpStatus.OK);
    // }


    //id truyền vào là id của PM
    @GetMapping("projectAndMember/{id}")
    public ResponseEntity<List<ProjectAndMemberDto>> getMemberByProjectId(@PathVariable("id") String id){
        List<ProjectAndMember> projectMember = projectMemberService.findProjectAndMemberByPMId(id);
        List<ProjectAndMemberDto> subProjectMems = projectMember.stream()
                        .map(projectMem -> modelMapper.map(projectMem, ProjectAndMemberDto.class))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(subProjectMems,HttpStatus.OK);
    }
}