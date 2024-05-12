package com.kreitek.kreitefy.kreitefy.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "styleSequence")
    private Long id;

    @Column(name = "style", length = 30, nullable = false)
    private String style;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL)
    Set<Song> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
