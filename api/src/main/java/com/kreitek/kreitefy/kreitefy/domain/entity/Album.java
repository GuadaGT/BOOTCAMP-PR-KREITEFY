package com.kreitek.kreitefy.kreitefy.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albumSequence")
    private Long id;

    @Column(name= "title", nullable = false)
    private String title;

    @Lob
    private String image;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Set<Song> songs;

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

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
