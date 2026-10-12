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

    //Test rechaza nombres en blanco.
    @Test
    public void testRejectBlankName(){
        User ethan = new User("Ethan", "correo@gmail.com", "123465789");
        Instant corroborar = ethan.getUpdatedAt();
        Assertions.assertThrows(
          IllegalArgumentException.class,
                () -> ethan.rename(" ")
        );
        Assertions.assertEquals("Ethan", ethan.getDisplayName());
        Assertions.assertEquals(corroborar, ethan.getUpdatedAt());
    }

    //Test rechazar nombres de 81 caracteres o mas.
    @Test
    public void testRejectName(){
        User ethan = new User("Ethan", "correo@gmail.com", "123465789");
        Instant corroborar = ethan.getUpdatedAt();
        String nombre = "a".repeat(81);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ethan.rename(nombre)
        );
        Assertions.assertEquals("Ethan", ethan.getDisplayName());
        Assertions.assertEquals(corroborar, ethan.getUpdatedAt());
    }

    //Test aceptar nombre de 80 caracteres.
    @Test
    public void testAcceptName(){
        User ethan = new User("Ethan", "correo@gmail.com", "123465789");
        String nombre = "a".repeat(80);
        ethan.rename(nombre);
        Assertions.assertEquals(nombre, ethan.getDisplayName());
    }

    //Test corroborar exepcion de nombre null.
    @Test
    public void testRejectNullName() {
        User ethan = new User("Ethan", "correo@gmail.com", "hash-de-prueba");
        Instant corroborar = ethan.getUpdatedAt();

        Assertions.assertThrows(
                NullPointerException.class,
                () -> ethan.rename(null)
        );

        Assertions.assertEquals("Ethan", ethan.getDisplayName());
        Assertions.assertEquals(corroborar, ethan.getUpdatedAt());
    }

}
