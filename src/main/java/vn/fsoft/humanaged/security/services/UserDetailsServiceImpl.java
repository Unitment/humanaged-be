package vn.fsoft.humanaged.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.Employee;
import vn.fsoft.humanaged.service.IEmployeeService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeService.findByAccountName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(employee);
    }
}