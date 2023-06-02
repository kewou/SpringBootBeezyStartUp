package org.example.services;

import org.example.exceptions.NoInstanceFoundException;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoInstanceFoundException("No user found with this id => " + id));
        return user;
    }

    public void delete(Long id) {
        User user = getUser(id);
        userRepository.deleteById(id);
        logger.info("L'utilisateur d'id " + id + " a été supprimé");
    }

    public User update(long id, User user) {
        User userUpdate = getUser(id);
        userUpdate.setName(user.getName());
        userUpdate.setTelephone(user.getTelephone());
        userRepository.save(userUpdate);
        logger.info("L'utilisateur d'id " + userUpdate.getId() + " a été mis à jour");
        return userUpdate;
    }

    public User register(User user) {
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser, "id");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return newUser;
    }

    public User partialUpdate(long id, User user) {
        User partialUpdateUser = getUser(id);
        if (user.getName() != null) {
            partialUpdateUser.setName(user.getName());
        }
        if (user.getTelephone() != 0) {
            partialUpdateUser.setTelephone(user.getTelephone());
        }
        userRepository.save(partialUpdateUser);
        return partialUpdateUser;
    }
}
