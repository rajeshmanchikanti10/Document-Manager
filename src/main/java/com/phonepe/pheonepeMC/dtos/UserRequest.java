package com.phonepe.pheonepeMC.dtos;

import com.phonepe.pheonepeMC.enums.Status;
import com.phonepe.pheonepeMC.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserRequest {
    private String mail;
    private String name;
    private Status status;
    private String password;

    public UserRequest(String mail){
        this.mail = mail;
    }

    public static User createUserDto(UserRequest userRequest){
        return User.builder()
                .userId(String.format("U%s", UUID.randomUUID().toString()))
                .email(userRequest.getMail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .status(Status.ACTIVE)
                .build();
    }
    public static UserRequest toUserRequest(User user) {
        return UserRequest.builder()
                .mail(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .status(user.getStatus())
                .build();
    }
}
