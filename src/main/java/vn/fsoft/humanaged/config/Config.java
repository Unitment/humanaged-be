package vn.fsoft.humanaged.config;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.domain.Gender;
import vn.fsoft.humanaged.domain.Project;
import vn.fsoft.humanaged.domain.ProjectMember;
import vn.fsoft.humanaged.domain.ProjectMemberKey;
import vn.fsoft.humanaged.domain.ProjectRole;
import vn.fsoft.humanaged.domain.ProjectState;
import vn.fsoft.humanaged.domain.Status;
import vn.fsoft.humanaged.repository.IEmployeeRepository;
import vn.fsoft.humanaged.repository.IProjectMemberRepository;
import vn.fsoft.humanaged.repository.IProjectRepository;

@Configuration
public class Config {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    // @Bean
    // public CommandLineRunner commandLineRunner(IEmployeeRepository employeeRepository, IProjectRepository projectRepository, IProjectMemberRepository projectMemberRepository){
    //     return args -> {
    //         Employee employee1 = new Employee("", "Huu Tinh", LocalDate.now(), Gender.MALE, "huutinh@mail.com", "Vietnam", "Quang Nam", "Hoi An", "Cam Nam", Status.WORKING, null, null, null);
    //         Employee employee2 = new Employee("", "Khanh Doan", LocalDate.now(), Gender.MALE, "khanhdoan@mail.com", "Vietnam", "Cao Bang", "Bao Lam", "Pac Miau", Status.PENDING, null, null, null);
    //         Employee employee3 = new Employee("", "Trung Hieu", LocalDate.now(), Gender.MALE, "trunghieu@mail.com", "Vietnam", "Quang Ninh", "Ha Long", "Ha Tu", Status.PENDING, null, null, null);
    //         Employee employee4 = new Employee("", "Nguyen Nhan", LocalDate.now(), Gender.MALE, "nguyennhan@mail.com", "USA", "Califonia", "Sacramento", "Rosemont", Status.PENDING, null, null, null);
    //         List<Employee> sEmployees = employeeRepository.saveAll(List.of(employee1, employee2, employee3, employee4));
    //         Project project1 = new Project("", "ProjectA", LocalDate.now(), LocalDate.now(), "test ProjectA", ProjectState.PROCESSING, null);
    //         Project project2 = new Project("", "ProjectB", LocalDate.now(), LocalDate.now(), "test ProjectB", ProjectState.PENDING, null);
    //         List<Project> sProjects = projectRepository.saveAll(List.of(project1, project2));
    //         ProjectMember projectMember1 = new ProjectMember(new ProjectMemberKey(sEmployees.get(0).getId(), sProjects.get(0).getId()), sEmployees.get(0), sProjects.get(0), ProjectRole.PM);
    //         ProjectMember projectMember2 = new ProjectMember(new ProjectMemberKey(sEmployees.get(0).getId(), sProjects.get(1).getId()), sEmployees.get(0), sProjects.get(1), ProjectRole.MEMBER);
    //         ProjectMember projectMember3 = new ProjectMember(new ProjectMemberKey(sEmployees.get(1).getId(), sProjects.get(0).getId()), sEmployees.get(1), sProjects.get(0), ProjectRole.MEMBER);
    //         ProjectMember projectMember4 = new ProjectMember(new ProjectMemberKey(sEmployees.get(2).getId(), sProjects.get(0).getId()), sEmployees.get(2), sProjects.get(0), ProjectRole.LEADER);
    //         ProjectMember projectMember5 = new ProjectMember(new ProjectMemberKey(sEmployees.get(3).getId(), sProjects.get(0).getId()), sEmployees.get(3), sProjects.get(0), ProjectRole.MEMBER);
    //         System.err.println(List.of(projectMember1, projectMember2, projectMember3));
    //         projectMemberRepository.saveAll(List.of(projectMember1, projectMember2, projectMember3, projectMember4, projectMember5));
    //         // projectMemberRepository.save(projectMember1);
    //     };
    // }
}