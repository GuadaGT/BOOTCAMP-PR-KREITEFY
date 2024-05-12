package com.kreitek.kreitefy.kreitefy.domain.entity;

import com.kreitek.kreitefy.kreitefy.domain.type.RolType;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;

    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolType rol;

    @OneToMany(mappedBy = "user")
    private Set<UserSong> userSongs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RolType getRol() {
        return rol;
    }

    public void setRol(RolType rol) {
        this.rol = rol;
    }

    public Set<UserSong> getUserSongs() {
        return userSongs;
    }

    public void setUserSongs(Set<UserSong> userSongs) {
        this.userSongs = userSongs;
    }
}
