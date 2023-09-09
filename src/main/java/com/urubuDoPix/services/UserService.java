package com.urubuDoPix.services;

import com.urubuDoPix.dtos.UserDTO;
import com.urubuDoPix.model.User;
import com.urubuDoPix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(UUID id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("Usuário não encontrado!"));
    }
    public User findUserByDocument(String document) throws Exception {
        return this.userRepository.findUserByDocument(document).orElseThrow(()-> new Exception("Usuário não encontrado!"));
    }
    public void saveUser(User user){
        this.userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }
}
