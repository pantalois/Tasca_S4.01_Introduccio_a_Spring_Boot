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
    public List<User> listAllUsers(@RequestParam(required = false) String name) {
        return users.stream()
                .filter(user -> name == null || name.isBlank() || user.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody User user) {
        users.add(user);
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable UUID id) {
       return users.stream()
                .filter(user -> user.getId().equals(id)).findFirst()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

}
