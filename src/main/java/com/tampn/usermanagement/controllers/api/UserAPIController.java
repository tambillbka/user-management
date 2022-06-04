package com.tampn.usermanagement.controllers.api;

import com.tampn.usermanagement.entities.User;
import com.tampn.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAPIController {
    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getListUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }
}
