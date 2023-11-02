package edu.syr.oodproject.trelloclonesu.services;

import edu.syr.oodproject.trelloclonesu.common.exceptions.InvalidOperationException;
import edu.syr.oodproject.trelloclonesu.jpa.repository.UserRepository;
import edu.syr.oodproject.trelloclonesu.models.User;
import edu.syr.oodproject.trelloclonesu.common.api.dao.CommonServiceAPI;
import edu.syr.oodproject.trelloclonesu.models.status.UserStatus;
import edu.syr.oodproject.trelloclonesu.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CommonServiceAPI<User> {

    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public Optional<User> get(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        if (!UserValidator.validateBeforeInsert(user))
            throw  new InvalidOperationException("User object not valid");
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public Optional<User> update(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getUserID());
    }

    @Override
    public void delete(User user) {
        user.setUserStatus(UserStatus.REMOVED);
        update(user);
    }
}
