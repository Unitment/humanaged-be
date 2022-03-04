package vn.fsoft.humanaged.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectRole;
import vn.fsoft.humanaged.dto.MemberDTO;
import vn.fsoft.humanaged.dto.ProjectAndMember;
import vn.fsoft.humanaged.dto.ProjectMemberDTO;
import vn.fsoft.humanaged.service.IProjectMemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projectMember")
public class ProjectMemberController {

    @Autowired
    private IProjectMemberService projectMemberService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("role/{role}")
    public ResponseEntity<List<ProjectMemberDTO>> getMemberByRole(@PathVariable("role") ProjectRole role) {
        List<ProjectMember> projectMember = projectMemberService.findMemberByRole(role);
        List<ProjectMemberDTO> subProjectMems = projectMember.stream()
                .map(projectMem -> modelMapper.map(projectMem, ProjectMemberDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subProjectMems, HttpStatus.OK);
    }

    //Lấy ra pm có id tương ứng
    @GetMapping("pm/{employeeId}")
    public ResponseEntity<List<ProjectMemberDTO>> getMemberByRole(@PathVariable("employeeId") String employeeId) {
        List<ProjectMember> projectMember = projectMemberService.findMemberByEmployeeId(employeeId);
        List<ProjectMemberDTO> subProjectMems = projectMember.stream()
                .map(projectMem -> modelMapper.map(projectMem, ProjectMemberDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subProjectMems, HttpStatus.OK);
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
    public ResponseEntity<List<ProjectAndMember>> getMemberByProjectId(@PathVariable("id") String id) {
        List<ProjectAndMember> projectMember = projectMemberService.findProjectAndMemberByPMId(id);
//        List<ProjectAndMemberDTO> subProjectMems = projectMember.stream()
//                        .map(projectMem -> modelMapper.map(projectMem, ProjectAndMemberDTO.class))
//                        .collect(Collectors.toList());
        return new ResponseEntity<>(projectMember, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addEmployeeToProject(@RequestBody MemberDTO memberDTO) {

        projectMemberService.addEmployeeToProject(memberDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/check-leader/{id}")
    public ResponseEntity<Boolean> checkIfProjectHasLeader(@PathVariable("id") String projectID) {

        return ResponseEntity.ok(projectMemberService.isProjectHasLeader(projectID));
    }
}