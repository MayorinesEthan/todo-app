package io.github.mayorinesethan.todo.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmailIgnoresCase() {
        User user = new User(
                "Ethan",
                "Ethan@example.com",
                "hash-de-prueba"
        );

        userRepository.saveAndFlush(user);

        Optional<User> result =
                userRepository.findByEmailIgnoreCase("ETHAN@EXAMPLE.COM");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(user.getId(), result.orElseThrow().getId());
    }

    @Test
    void rejectsDuplicateEmailIgnoringCase() {
        User firstUser = new User(
                "Ethan",
                "Ethan@example.com",
                "hash-de-prueba"
        );

        userRepository.saveAndFlush(firstUser);

        User secondUser = new User(
                "Otro usuario",
                "ETHAN@EXAMPLE.COM",
                "otro-hash-de-prueba"
        );

        Assertions.assertThrows(
                DataIntegrityViolationException.class,
                () -> userRepository.saveAndFlush(secondUser)
        );
    }
}
