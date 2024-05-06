package com.phonepe.pheonepeMC.services;

import com.phonepe.pheonepeMC.dtos.UserRequest;
import com.phonepe.pheonepeMC.enums.Status;
import com.phonepe.pheonepeMC.exceptions.UserException;
import com.phonepe.pheonepeMC.models.User;
import com.phonepe.pheonepeMC.repository.UserRepo;

public class UserService {
    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserRequest createUser(UserRequest userRequest) throws UserException {
        User user = UserRequest.createUserDto(userRequest);
        if (userRepo.getUser(user.getEmail()) != null) {
            throw new UserException(user.getUserId(), "UserAlready Exists");
        }
        return UserRequest.toUserRequest(userRepo.createUser(user));
    }

    public UserRequest getUser(String email) throws UserException {
        if (userRepo.getUser(email) == null) {
            throw new UserException(email, "user Doesn't Exists");
        }
        return UserRequest.toUserRequest(userRepo.getUser(email));
    }

    public String deleteUser(String email) throws UserException {
        if (userRepo.getUser(email) == null) {
            throw new UserException(email, "user Doesn't Exists");
        }
        User user = userRepo.getUser(email);
        user.setStatus(Status.INACTIVE);
        userRepo.deleteUser(user);
        return user.getUserId();
    }

}
