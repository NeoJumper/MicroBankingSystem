package com.kcc.banking.common.auth;

import com.kcc.banking.domain.employee.dto.response.EmployeeDetail;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetail implements UserDetails {

    private EmployeeDetail employeeDetail;

    public PrincipalDetail(EmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority(){

            @Override
            public String getAuthority() {
                return employeeDetail.getRoles();
            }
        });


        return collect;
    }

    @Override
    public String getPassword() {
        return employeeDetail.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(employeeDetail.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
