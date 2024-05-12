package com.kreitek.kreitefy.kreitefy.application.dto;

import java.io.Serializable;
import java.util.Objects;

public class SongSimpleDto implements Serializable {

    private Long id;
    private String title;
    private Long artistId;
    private String artistName;
    private Long albumId;
    private String album;
    private Long styleId;
    private String style;
    private String image;

    public SongSimpleDto() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        SongSimpleDto that = (SongSimpleDto) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(artistId, that.artistId) && Objects.equals(artistName, that.artistName) && Objects.equals(albumId, that.albumId) && Objects.equals(album, that.album) && Objects.equals(styleId, that.styleId) && Objects.equals(style, that.style) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artistId, artistName, albumId, album, styleId, style, image);
    }

    @Override
    public String toString() {
        return "SongSimpleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", albumId=" + albumId +
                ", album='" + album + '\'' +
                ", styleId=" + styleId +
                ", style='" + style + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
