package project.denis.spring.boot_security.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.denis.spring.boot_security.rest.dao.UserDao;
import project.denis.spring.boot_security.rest.dto.UserDTO;
import project.denis.spring.boot_security.rest.models.User;
import project.denis.spring.boot_security.rest.util.UserNotFoundException;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {

        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void addUser(UserDTO user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }


    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {

        User user = userDao.getUser(id);
        if (user == null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Transactional
    @Override
    public void editUser(Long id, UserDTO user) {

        if (user.getPassword() == "") {
            user.setPassword(userDao.getUser(id).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.editUser(id, user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {

        userDao.deleteUser(id);
    }
}
