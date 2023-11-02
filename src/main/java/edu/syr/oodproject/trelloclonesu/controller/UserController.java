package edu.syr.oodproject.trelloclonesu.controller;

import edu.syr.oodproject.trelloclonesu.common.exceptions.InvalidOperationException;
import edu.syr.oodproject.trelloclonesu.common.exceptions.UserNotFoundException;
import edu.syr.oodproject.trelloclonesu.models.User;
import edu.syr.oodproject.trelloclonesu.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/app/v1")
public class UserController {

    @Autowired
    private UserService userDaoService;

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> usersList = userDaoService.getAll().orElse(new ArrayList<User>());
        return new ResponseEntity<>(usersList,HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user = userDaoService.get(id).orElseThrow(()->new UserNotFoundException("user doesn't exist"));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> addUser( @Valid @RequestBody User user){
        userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUserID())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<User> modifyUserDetails(@PathVariable int id, @RequestBody User user){
        user.setUserID(id);
        User updatedUser = userDaoService.update(user).
                orElseThrow(()-> new InvalidOperationException("Cannot update a user who doesn't exists"));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("").buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable int id) {
        User user = userDaoService.get(id).orElseThrow(()->new InvalidOperationException("user doesn't exist"));
        userDaoService.delete(user);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}
