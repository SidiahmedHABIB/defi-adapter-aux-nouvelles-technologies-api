package net.sda.myresumeapi.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String fname;
    private String lname;
    private String email;
    private String password;
}