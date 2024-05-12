package com.kreitek.kreitefy.kreitefy.domain.entity.associative;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserSongAssociative implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "song_id")
    private Long songId;

    public UserSongAssociative() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSongAssociative that = (UserSongAssociative) o;
        return Objects.equals(userId, that.userId) && Objects.equals(songId, that.songId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, songId);
    }

    @Override
    public String toString() {
        return "UserSongAssociative{" +
                "userId='" + userId + '\'' +
                ", songId=" + songId +
                '}';
    }
}

