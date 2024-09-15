package com.kcc.banking.domain.employee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class EmployeeDetail {

    private int id;
    private String name;
    private String password;
    private Timestamp hireDate;
    private String email;
    private String phoneNumber;
    private int vacationDays;
    private String joinState;
    private String deptName;
    private String position;
    private String authority;
    private String verified;

    @Builder
    public EmployeeDetail(int id, String name, String password, Timestamp hireDate, String email, String phoneNumber, int vacationDays, String joinState, String deptName, String position, String authority, String verified) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.hireDate = hireDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.vacationDays = vacationDays;
        this.joinState = joinState;
        this.deptName = deptName;
        this.position = position;
        this.authority = authority;
        this.verified = verified;
    }

    public void convertStatusAndAuthority() {

        if (this.joinState.equals("TRUE"))
            this.joinState = "O";
        else
            this.joinState = "X";


        if(this.authority.equals("ROLE_TOP_APPROVAL"))
            this.authority = "최고";
        else if (this.authority.equals("ROLE_FIRST_APPROVAL")) {
            this.authority = "1차";
        }
        else if (this.authority.equals("ROLE_SECOND_APPROVAL")) {
            this.authority = "2차";
        }
        else
            this.authority = "권한 없음";



    }



}
