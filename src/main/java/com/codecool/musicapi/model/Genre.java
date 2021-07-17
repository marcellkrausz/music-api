package com.codecool.musicapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@JsonIgnoreProperties("songs")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Song> songs = new HashSet<>();

    public void addSong(Song song) {
        if (song.getGenres() == null) {
            song.getGenres().add(this);
            this.songs.add(song);
        }
    }
}
