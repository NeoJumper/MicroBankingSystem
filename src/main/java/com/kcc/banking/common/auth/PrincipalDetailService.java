package com.kcc.banking.common.auth;


import com.kcc.banking.domain.employee.dto.response.EmployeeDetail;
import com.kcc.banking.domain.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final EmployeeMapper employeeMapper;

    @Override
    public UserDetails loadUserByUsername(String id) {
        EmployeeDetail employee = employeeMapper.findById(Long.parseLong(id));

        if (employee == null) {
            throw new UsernameNotFoundException("UserNotFound");
        }

        return new PrincipalDetail(employee);
    }
}
