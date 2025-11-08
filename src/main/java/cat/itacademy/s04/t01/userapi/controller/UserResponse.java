package cat.itacademy.s04.t01.userapi.controller;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email) {
}
