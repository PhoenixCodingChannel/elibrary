package com.example.elibrary.models.request;

import com.example.elibrary.models.Student;
import com.example.elibrary.models.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentCreateRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    private String address;

    public Student toStudent(){
        return Student.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }
}
