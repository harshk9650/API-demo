package com.lpu.apidemo.service;

import com.lpu.apidemo.model.UserDTO;
import com.lpu.apidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public UserDTO create(UserDTO user) {
        // -->service layer will call repository for db operation
        return repository.save(user);
    }

    public UserDTO getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public UserDTO update(UserDTO dto) {
        UserDTO userDTO = repository.findById(dto.getId()).orElse(null);
        if (userDTO!=null){
            return repository.save(dto);
        }
        return null;
    }

    public UserDTO delete(int id) {
        UserDTO dto = repository.findById(id).orElse(null);
        if (dto!=null){
            repository.delete(dto);
            return  dto;
        }
        return null;
    }

    public List<UserDTO> getUser() {
        return repository.findAll();
    }
}
