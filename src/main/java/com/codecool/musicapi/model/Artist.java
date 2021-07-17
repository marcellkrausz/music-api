package com.codecool.musicapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIgnoreProperties("songs")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(mappedBy = "artists")
    private Set<Song> songs = new HashSet<>();

    public void addAlbum(Album album) {
        if (album.getArtist() == null) {
            album.setArtist(this);
            this.albums.add(album);
        }
    }
}
