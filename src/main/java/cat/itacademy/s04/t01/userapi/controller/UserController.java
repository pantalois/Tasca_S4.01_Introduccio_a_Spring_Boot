package cat.itacademy.s04.t01.userapi.controller;

import cat.itacademy.s04.t01.userapi.exception.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.model.User;
import cat.itacademy.s04.t01.userapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> listAllUsers(@RequestParam(required = false) String name) {
        List<User> users = userService.searchByName(name == null ? "" : name);
        return users.stream()
                .filter(user -> name == null || name.isBlank() || user.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable UUID id) {
        Optional<User> users = userService.findById(id);
        return users.stream()
                .filter(user -> user.getId().equals(id)).findFirst()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

}
