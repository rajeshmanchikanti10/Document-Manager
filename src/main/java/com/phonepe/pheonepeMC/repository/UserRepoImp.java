package com.phonepe.pheonepeMC.repository;

import com.phonepe.pheonepeMC.models.User;

import java.util.HashMap;

public class UserRepoImp  implements  UserRepo{
    HashMap<String, User>   users = new HashMap<>();

    @Override
    public User createUser(User user) {
        users.put(user.getEmail(),user);
        return users.get(user);
    }

    @Override
    public User getUser(String email) {
        return users.get(email);
    }


    @Override
    public String deleteUser(User  user) {
         users.put(user.getUserId(),user);
         return user.getUserId();
    }

    @Override
    public User getUserByEmail(String  email) {
        return users.get(email);
    }
}
