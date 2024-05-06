package com.phonepe.pheonepeMC.utils;

import com.phonepe.pheonepeMC.exceptions.LoginException;
import com.phonepe.pheonepeMC.repository.AccessRepoImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

public class LoginUtil {


    @Autowired
    private AccessRepoImp accessRepoImp;

    public void validateKey(String loginKey) throws LoginException {
        if (accessRepoImp.getAccessKeyDetails(loginKey).getTtl().isBefore(Instant.now())) {

            throw new LoginException("key expired");

        }
    }
}
