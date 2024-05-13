package com.kreitek.kreitefy.kreitefy.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SongDto implements Serializable {

    private Long id;
    private String title;
    private String imageAlbum;
    private Date date;
    private Float duration;
    private Long rating;
    private Long reproductions;
    private Long artistId;
    private String artistName;
    private Long albumId;
    private String album;
    private Long styleId;
    private String style;

    public SongDto() {}


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

    public String getImageAlbum() {
        return imageAlbum;
    }

    public void setImageAlbum(String imageAlbum) {
        this.imageAlbum = imageAlbum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getReproductions() {
        return reproductions;
    }

    public void setReproductions(Long reproductions) {
        this.reproductions = reproductions;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongDto songDto = (SongDto) o;
        return Objects.equals(id, songDto.id) && Objects.equals(title, songDto.title) && Objects.equals(imageAlbum, songDto.imageAlbum) && Objects.equals(date, songDto.date) && Objects.equals(duration, songDto.duration) && Objects.equals(rating, songDto.rating) && Objects.equals(reproductions, songDto.reproductions) && Objects.equals(artistId, songDto.artistId) && Objects.equals(artistName, songDto.artistName) && Objects.equals(albumId, songDto.albumId) && Objects.equals(album, songDto.album) && Objects.equals(styleId, songDto.styleId) && Objects.equals(style, songDto.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imageAlbum, date, duration, rating, reproductions, artistId, artistName, albumId, album, styleId, style);
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageAlbum='" + imageAlbum + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", rating=" + rating +
                ", reproductions=" + reproductions +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", albumId=" + albumId +
                ", album='" + album + '\'' +
                ", styleId=" + styleId +
                ", style='" + style + '\'' +
                '}';
    }
}
