package com.kreitek.kreitefy.kreitefy.application.dto;

import com.kreitek.kreitefy.kreitefy.domain.entity.associative.UserSongAssociative;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserSongDto implements Serializable {

    private Long songId;
    private Long userId;
    private Long reproductions;
    private Long rating;
    private LocalDateTime reproductionDate;

    public UserSongDto() {
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReproductions() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSongDto that = (UserSongDto) o;
        return Objects.equals(songId, that.songId) && Objects.equals(userId, that.userId) && Objects.equals(reproductions, that.reproductions) && Objects.equals(rating, that.rating) && Objects.equals(reproductionDate, that.reproductionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, userId, reproductions, rating, reproductionDate);
    }

    @Override
    public String toString() {
        return "UserSongDto{" +
                "songId=" + songId +
                ", userId=" + userId +
                ", reproductions=" + reproductions +
                ", rating=" + rating +
                ", reproductionDate=" + reproductionDate +
                '}';
    }
}
