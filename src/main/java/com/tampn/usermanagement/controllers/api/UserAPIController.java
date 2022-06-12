package com.tampn.usermanagement.controllers.api;

import com.tampn.usermanagement.entities.User;
import com.tampn.usermanagement.payloads.responses.FindUserResponse;
import com.tampn.usermanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.tampn.usermanagement.services.UserService.validateEmptyRequest;

@RestController
@Slf4j
@RequestMapping("/api")
public class UserAPIController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<FindUserResponse> getListUser(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer size,
            @RequestParam(required = false, defaultValue = "") String search) {
        try {
            FindUserResponse response = userService.getUser(search, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            if (!validateEmptyRequest(user)) {
                return ResponseEntity.badRequest().build();
            }
            User response = userService.createUser(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            User response = userService.updateUser(user);
            return ResponseEntity.ok(response);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
