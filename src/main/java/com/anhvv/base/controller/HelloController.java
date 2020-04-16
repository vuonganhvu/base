package com.anhvv.base.controller;

import com.anhvv.base.common.BusinessException;
import com.anhvv.base.common.ResponseBase;
import com.anhvv.base.entity.User;
import com.anhvv.base.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HelloController {

    private final UserRepository userRepository;

    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/business-exceptions")
    public ResponseEntity<String> getBusinessException() throws Exception {
        throw new BusinessException("501", "Error business exception");
    }

    @GetMapping("api/exceptions")
    public ResponseEntity<String> getException() throws Exception {
        throw new Exception("501");
    }

    @GetMapping("/public/users")
    public ResponseEntity<List<User>> getPublicAllUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/login")
    public ResponseEntity<String> getLogin(){
        return ResponseEntity.ok("OK");
    }

}
