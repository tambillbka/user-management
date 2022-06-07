package com.tampn.usermanagement.controllers.api;

import com.tampn.usermanagement.payloads.responses.FindUserResponse;
import com.tampn.usermanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
