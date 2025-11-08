package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface UserService {
    User createUser(String name, String email);
    List<User> searchByName(String name);
    Optional<User> findById(UUID id);
}
