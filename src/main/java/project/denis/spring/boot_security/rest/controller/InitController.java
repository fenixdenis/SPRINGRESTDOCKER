package project.denis.spring.boot_security.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.denis.spring.boot_security.rest.dao.RoleDao;
import project.denis.spring.boot_security.rest.dto.UserDTO;
import project.denis.spring.boot_security.rest.services.UserService;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class InitController {
    private final UserService userservice;
    private final RoleDao roleDao;

    @Autowired
    public InitController(UserService userservice, RoleDao roleDao) {
        this.userservice = userservice; this.roleDao = roleDao;
    }


    @GetMapping("/init")
    public String  InitUser() {

        if (userservice.listUsers().isEmpty()) {
            roleDao.initRoles();
            userservice.addUser(new UserDTO("admin", "admin", new Long[]{(long) 1}));

        }
        return "redirect:/";
    }
    @GetMapping("/")
    public String adminPage() {
        return "admin"; // Отображает admin.html (или admin.jsp, в зависимости от настройки шаблонизатора)
    }

    @GetMapping("/user")
    public String userPage() {
        return "user"; // Отображает user.html
    }
}





