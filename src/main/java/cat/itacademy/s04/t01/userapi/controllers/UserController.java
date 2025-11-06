package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private static List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public List<User> listAllUsers() {
        return users;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        users.add(user);
        return "Created user with" +
                "user name " + user.getName() + " and email " + user.getEmail()
                + " UUID = " + user.getId() ;
    }

    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable UUID id) {
       return users.stream()
                .filter(user -> user.getId().equals(id)).findFirst()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

}
