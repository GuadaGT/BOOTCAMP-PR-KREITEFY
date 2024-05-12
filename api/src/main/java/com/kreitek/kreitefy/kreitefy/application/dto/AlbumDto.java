package com.kreitek.kreitefy.kreitefy.application.dto;

import java.io.Serializable;
import java.util.Objects;

public class AlbumDto implements Serializable {

    private Long id;
    private String title;
    private String image;

    public AlbumDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumDto albumDto = (AlbumDto) o;
        return Objects.equals(id, albumDto.id) && Objects.equals(title, albumDto.title) && Objects.equals(image, albumDto.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, image);
    }

    @Override
    public String toString() {
        return "AlbumDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
