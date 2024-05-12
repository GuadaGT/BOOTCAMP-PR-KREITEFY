package com.kreitek.kreitefy.kreitefy.application.dto;
import com.kreitek.kreitefy.kreitefy.domain.type.RolType;
import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    protected RolType rol;


    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolType getRol() {
        return rol;
    }

    public void setRol(RolType rol) {
        this.rol = rol;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(username, userDto.username) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                rol == userDto.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, firstName, lastName, rol);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rol=" + rol +
                '}';
    }
}
