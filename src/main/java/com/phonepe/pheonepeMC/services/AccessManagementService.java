package com.phonepe.pheonepeMC.services;

import com.phonepe.pheonepeMC.exceptions.LoginException;
import com.phonepe.pheonepeMC.repository.AccessRepoImp;
import com.phonepe.pheonepeMC.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AccessManagementService {

    @Autowired
    private AccessRepoImp accessRepoImp;

    @Autowired
    private UserRepo userRepo;

    public String logIn(String email,String password) throws LoginException {

        if(userRepo.getUser(email).getPassword().equals(password)){
            String accessKey = UUID.randomUUID().toString();
            accessRepoImp.generateAccessKey(email+"_"+password,accessKey);
            accessRepoImp.generateAccessKeyDetails(accessKey);
            return accessKey;
        }
        throw new LoginException("Invalid credentials");

    }
}
