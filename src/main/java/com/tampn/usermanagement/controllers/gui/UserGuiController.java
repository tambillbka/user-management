package com.tampn.usermanagement.controllers.gui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserGuiController {
    @GetMapping(name = "/")
    public String userScreen() {
        return "index";
    }
}
