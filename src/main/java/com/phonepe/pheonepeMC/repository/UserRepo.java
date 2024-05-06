package com.phonepe.pheonepeMC.repository;

import com.phonepe.pheonepeMC.models.User;

public interface UserRepo {
    public User createUser(User user);
    public User getUser(String userId);
    public String deleteUser(User user);
    public User getUserByEmail(String email);
}
