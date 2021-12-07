package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.User;
import qlpk.repo.UserRepo;
import qlpk.service.UserService;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public User getUserByName(String username) {
        return userRepo.findByUserName(username);
    }

    @Override
    public boolean save(User user) {
        userRepo.save(user);
        return true;
    }
}
