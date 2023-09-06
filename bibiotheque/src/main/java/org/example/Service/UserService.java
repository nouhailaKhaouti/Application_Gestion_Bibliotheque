package org.example.Service;

import org.example.Model.User;
import org.example.Repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(User userC) {
        User user = userRepository.findByUsername(userC);
        if(user.getPassword().equals(userC.getPassword()) && user!=null){
            return user;
        }else{
            return null;
        }
    }
}
