package com.phonepe.pheonepeMC.models;

import com.phonepe.pheonepeMC.enums.Status;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class User {
    private String userId;
    private String password;
    private String email;
    private String name;
    private Status status;

}
