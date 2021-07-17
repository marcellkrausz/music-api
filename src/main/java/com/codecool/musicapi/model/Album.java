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
@JsonIgnoreProperties("artist")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Artist artist;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private Set<Song> songs = new HashSet<>();

    public void addSong(Song song) {
        if (song.getAlbum() == null) {
            song.setAlbum(this);
            this.songs.add(song);
        }
    }
 }
