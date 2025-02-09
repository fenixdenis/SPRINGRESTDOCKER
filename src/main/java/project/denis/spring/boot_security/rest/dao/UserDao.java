package project.denis.spring.boot_security.rest.dao;

import project.denis.spring.boot_security.rest.dto.UserDTO;
import project.denis.spring.boot_security.rest.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(UserDTO user);

    List<User> listUsers();

    User getUser(Long id);

    void editUser(Long id, UserDTO user);

    void deleteUser(Long id);

    List<User> findByUsername(String username);
}