package com.kreitek.kreitefy.kreitefy.domain.entity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "songSequence")
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Lob
    @Column(name = "image", nullable = false)
    private String imageAlbum;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "duration", nullable = true)
    private Float duration;

    @Column(name = "rating", nullable = true)
    private Long rating;

    @Column(name = "reproductions", nullable = true)
    private Long reproductions = 0L;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private Set<UserSong> userSongs;

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
        this.reproductions = reproductions == null ? 0 : reproductions;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Set<UserSong> getUserSongs() {
        return userSongs;
    }

    public void setUserSongs(Set<UserSong> userSongs) {
        this.userSongs = userSongs;
    }
}
