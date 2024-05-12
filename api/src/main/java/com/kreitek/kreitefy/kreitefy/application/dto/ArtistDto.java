package com.kreitek.kreitefy.kreitefy.application.dto;

import java.io.Serializable;
import java.util.Objects;

public class ArtistDto implements Serializable {

    private Long id;
    private String name;

    public ArtistDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDto artistDto = (ArtistDto) o;
        return Objects.equals(id, artistDto.id) && Objects.equals(name, artistDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ArtistDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
