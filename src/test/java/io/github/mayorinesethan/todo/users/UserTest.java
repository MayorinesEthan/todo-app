package io.github.mayorinesethan.todo.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.Instant;

public class UserTest {

    //Test para funcion Rename.
    @Test
    public void testRename(){
        User ethan = new User ("Ethan", "correo@gmail.com", "123456789");
        ethan.rename(" Nuevo nombre ");
        Assertions.assertEquals("Nuevo nombre", ethan.getDisplayName());
    }

    //Test para comprobar que no cambia updatedAt en Rename si no hay cambio de nombre.
    @Test
    public void testUpdatedAtRename(){
        User ethan = new User("Ethan", "correo@gmail.com", "123456789");
        Instant corroborar = ethan.getUpdatedAt();
        ethan.rename(" Ethan ");
        Assertions.assertEquals("Ethan", ethan.getDisplayName());
        Assertions.assertEquals(corroborar, ethan.getUpdatedAt());
    }
}
