package com.kreitek.kreitefy.kreitefy.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reproductions")
public class Reproductions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reproductionSequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @Column(name = "reproduction_date")
    private LocalDateTime reproductionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public LocalDateTime getReproductionDate() {
        return reproductionDate;
    }

    public void setReproductionDate(LocalDateTime reproductionDate) {
        this.reproductionDate = reproductionDate;
    }
}
