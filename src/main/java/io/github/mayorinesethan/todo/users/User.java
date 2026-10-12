package io.github.mayorinesethan.todo.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "app_users")
public class User {

    @Id
    private UUID id;

    @Column(name = "display_name", nullable = false, length = 80)
    private String displayName;

    @Column(name = "email", nullable = false, length = 254)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected User(){

    }

    public User (String displayName, String email, String passwordHash){
        this.id = UUID.randomUUID();

        this.displayName = Objects.requireNonNull(displayName, "El nombre no puede ser nulo.").strip();
        if (this.displayName.isBlank()){
            throw new IllegalArgumentException("Nombre no puede estar vacio.");
        }
        if (this.displayName.length() > 80){
            throw new IllegalArgumentException("El nombre debe tener maximo 80 caracteres.");
        }

        this.email = Objects.requireNonNull(email, "El email no puede ser nulo.").strip();
        if (this.email.isBlank()){
            throw new IllegalArgumentException("El email no puede estar vacio.");
        }
        if (this.email.length() > 254){
            throw new IllegalArgumentException("El email tiene como maximo 254 caracteres.");
        }

        this.passwordHash = Objects.requireNonNull(passwordHash,"La contraseña no puede ser nula.");
        if (this.passwordHash.isBlank()){
            throw new IllegalArgumentException("La contraseña no puede estar vacio.");
        }

        this.createdAt = Instant.now();
        this.updatedAt = createdAt;
    }

    //Funcion para renombrar.
    public void rename(String newDisplayName){
        String cleanDisplayName = Objects.requireNonNull(newDisplayName, "El nuevo nombre no puede ser nulo.").strip();
        if (cleanDisplayName.length() > 80){
            throw new IllegalArgumentException("El nuevo nombre debe tener como maximo 80 caracteres.");
        }
        if (cleanDisplayName.isBlank()){
            throw new IllegalArgumentException("El nuevo nombre no puede estar vacio.");
        }
        if (cleanDisplayName.equals(this.displayName)) return;
        this.displayName = cleanDisplayName;
        this.updatedAt = Instant.now();
    }

    //Getters
    public UUID getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
