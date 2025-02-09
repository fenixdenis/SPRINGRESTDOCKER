package project.denis.spring.boot_security.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.denis.spring.boot_security.rest.dto.UserDTO;
import project.denis.spring.boot_security.rest.models.User;
import project.denis.spring.boot_security.rest.services.UserService;
import project.denis.spring.boot_security.rest.services.security.AccountDetails;
import project.denis.spring.boot_security.rest.util.UserErrorResponse;
import project.denis.spring.boot_security.rest.util.UserNotFoundException;


import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminUserController {
    private UserService userservice;

    @Autowired
    public AdminUserController(UserService userservice) {
        this.userservice = userservice;
    }


    @GetMapping("/users")
    public List<User> UsersList() {

        return userservice.listUsers();
    }


    @GetMapping("/users/{id}")
    public User showUser(@PathVariable("id") Long id) {

        return userservice.getUser(id);
    }


    @GetMapping("/user")
    public User currentUser(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        return accountDetails.getUser();
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> userException(UserNotFoundException exception) {

        UserErrorResponse response = new UserErrorResponse(
                "User not found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> addUser(@RequestBody UserDTO user) {

        System.out.println(user);
        userservice.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> editUser(@RequestBody UserDTO user, @PathVariable("id") Long id) {

        userservice.editUser(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {

        userservice.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}




