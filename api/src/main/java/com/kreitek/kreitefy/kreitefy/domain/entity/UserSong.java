package com.kreitek.kreitefy.kreitefy.domain.entity;

import com.kreitek.kreitefy.kreitefy.domain.entity.associative.UserSongAssociative;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "userSongs")
public class UserSong {

    @EmbeddedId
    private UserSongAssociative id;

    @ManyToOne
    @MapsId("songId")
    @JoinColumn(name = "song_Id")
    private Song song;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_Id")
    private User user;

    @Column(name = "reproductions")
    private Long reproductions;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "reproduction_date")
    private LocalDateTime reproductionDate;

    public UserSongAssociative getId() {
        return id;
    }

    public void setId(UserSongAssociative id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getReproductions() {
       if(this.reproductions == null){
           this.reproductions = 0L;
       }
        return reproductions;
    }

    public void setReproductions(Long reproductions) {
        this.reproductions = reproductions;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public LocalDateTime getReproductionDate() {
        return reproductionDate;
    }

    public void setReproductionDate(LocalDateTime reproductionDate) {
        this.reproductionDate = reproductionDate;
    }
}
