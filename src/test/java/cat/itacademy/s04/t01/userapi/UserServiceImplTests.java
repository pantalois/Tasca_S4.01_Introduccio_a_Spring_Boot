package cat.itacademy.s04.t01.userapi;

import cat.itacademy.s04.t01.userapi.exception.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.model.User;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import cat.itacademy.s04.t01.userapi.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTests {


    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        User user = new User();
        user.setEmail("email");
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    void createUser_shouldGenerateUUIDAndSaveUserWhenEmailIsUnique() {
        User user = new User();
        user.setEmail("lois@gmail.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser.getId(), "El UUID debería haberse generado");
        verify(userRepository).save(savedUser);
    }
}
