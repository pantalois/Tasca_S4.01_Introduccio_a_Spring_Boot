package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

}
