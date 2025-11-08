package cat.itacademy.s04.t01.userapi;

import cat.itacademy.s04.t01.userapi.model.User;
import cat.itacademy.s04.t01.userapi.repository.InMemoryUserRepository;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InMemoryUserRepositoryTests {

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    @Qualifier("InMemoryUserRepository")
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user2 = new User();
        user3 = new User();
        UUID uuid = UUID.randomUUID();
    }

    @AfterEach
    void tearDown() {
        ((InMemoryUserRepository) userRepository).clear();
    }


    @Test
    public void save_shouldAddUserToList() {
        userRepository.save(user1);
        assertEquals(1, userRepository.findAll().size());

    }

    @Test
    public void findAll_shouldReturnAllUsers() {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void findUserById_shouldReturnUser() {
        userRepository.save(user1);
        assertEquals(user1, userRepository.findById(user1.getId()).get());
    }

    @Test
    public void searchByName_shouldReturnListOfUsersWithCoincidence() {
        user1.setName("Lois");
        userRepository.save(user1);
        assertEquals("Lois", userRepository.searchByName("lo").get(0).getName());
    }

    @Test
    public void existByEmail_shouldReturnTrueOrFalse() {
        user1.setEmail("lois@gmail.com");
        userRepository.save(user1);
        assertTrue(userRepository.existsByEmail("LOIS@GMAIL.COM"));
        assertFalse(userRepository.existsByEmail("loisito@gmail.com"));
    }


}
