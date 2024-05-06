package com.phonepe.pheonepeMC.repository;

import com.phonepe.pheonepeMC.models.AccessKeyDetails;

import java.time.Instant;
import java.util.HashMap;

public class AccessRepoImp {
    HashMap<String, AccessKeyDetails> accessKeyDetails = new HashMap<>();
    HashMap<String, String> accessKey= new HashMap<>();

    public AccessKeyDetails getAccessKeyDetails(String loginKey){
        return accessKeyDetails.get(loginKey);
    }

    public void generateAccessKeyDetails(String loginKey){
        AccessKeyDetails accessKeyDetails = AccessKeyDetails.builder()
                .accessKey(loginKey)
                .ttl(Instant.now().plusSeconds(1800))
                .build();
        this.accessKeyDetails.put(loginKey,accessKeyDetails);
    }

    public String getAccessKey(String cred){
        return accessKey.get(cred);
    }

    public void generateAccessKey(String cred,String key){
        accessKey.put(cred,key);
    }
}
