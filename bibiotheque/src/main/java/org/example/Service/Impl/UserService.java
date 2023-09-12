package org.example.Service.Impl;

import org.example.Model.User;
import org.example.Repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(User userC) {
        User user = userRepository.findByUsername(userC);
        if(user!=null && user.getPassword().equals(userC.getPassword())){
            return user;
        }else{
            return null;
        }
    }
}
