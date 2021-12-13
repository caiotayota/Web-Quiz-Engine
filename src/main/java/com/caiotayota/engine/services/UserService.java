package com.caiotayota.engine.services;

import com.caiotayota.engine.entities.User;
import com.caiotayota.engine.exceptions.UsernameExistsException;
import com.caiotayota.engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    public void save(User user) {
        Optional<User> userToSave = userRepo.findById(user.getEmail());
        if (userToSave.isPresent()) {
            throw new UsernameExistsException();
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepo.save(user);
        }
    }
}
