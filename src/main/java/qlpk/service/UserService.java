package qlpk.service;


import qlpk.entity.User;

public interface UserService {
    User getUserByName(String username);
    boolean save(User user);
}
