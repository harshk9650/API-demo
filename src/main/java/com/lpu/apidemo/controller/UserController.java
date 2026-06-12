package com.lpu.apidemo.controller;

import com.lpu.apidemo.exception.UserNotFoundException;
import com.lpu.apidemo.model.ErrorDetails;
import com.lpu.apidemo.model.ResponseDTO;
import com.lpu.apidemo.model.UserDTO;
import com.lpu.apidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private ResponseDTO responseDTO;


    @GetMapping("/hi")
    public ResponseEntity<String> hi(){
        System.out.println("hi() executed...");
        return new ResponseEntity<String>("Hi response from controller", HttpStatus.OK);
    }
    @PostMapping("/api/users")
    public ResponseEntity<ResponseDTO> create(@RequestBody UserDTO user){

        UserDTO savedUser=service.create(user);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setError(false);
        responseDTO.setMessage("User created successfully");
        List<UserDTO> dtos = new ArrayList<>();
        dtos.add(savedUser);
        responseDTO.setDtos(dtos);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);//201
    }

    @GetMapping("/api/users")
    public ResponseEntity<ResponseDTO> getUsers(){
        List<UserDTO> dtos = service.getUser();

        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setError(false);
        responseDTO.setMessage("User fetched successfully");
        responseDTO.setDtos(dtos);
        if(true) {
           throw  new RuntimeException();
        }
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);//200
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id){
        UserDTO dto = service.getUserById(id);
        if (dto!=null) return new ResponseEntity<>(dto,HttpStatus.OK);//200
        if(dto==null) throw new UserNotFoundException("User not found for the given id: "+id);
        return new ResponseEntity<UserDTO>(dto,HttpStatus.NOT_FOUND);//404
    }

    @PutMapping("/api/users")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto){
        UserDTO updateDto=service.update(dto);
        if (updateDto!=null) {
            return new ResponseEntity<>(updateDto,HttpStatus.OK);//200
        }
        return new ResponseEntity<UserDTO>(updateDto,HttpStatus.NOT_FOUND);//404
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable("id") int id){
        UserDTO deletedDTO=service.delete(id);
        if (deletedDTO!=null) return new ResponseEntity<>(deletedDTO,HttpStatus.OK);//200
        return new ResponseEntity<UserDTO>(deletedDTO,HttpStatus.NOT_FOUND);//404
    }
}
