package com.kreitek.kreitefy.kreitefy.application.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ReproductionsDto implements Serializable {

    private Long id;
    private Long userId;
    private Long songId;
    private LocalDate reproductionDate;

    public ReproductionsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getReproductionDate() {
        return reproductionDate;
    }

    public void setReproductionDate(LocalDate reproductionDate) {
        this.reproductionDate = reproductionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReproductionsDto that = (ReproductionsDto) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(songId, that.songId) && Objects.equals(reproductionDate, that.reproductionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, songId, reproductionDate);
    }

    @Override
    public String toString() {
        return "ReproductionsDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", songId=" + songId +
                ", reproductionDate=" + reproductionDate +
                '}';
    }
}
