package com.codecool.musicapi.services;


import com.codecool.musicapi.commands.GenreCommand;
import com.codecool.musicapi.model.Genre;
import com.codecool.musicapi.model.Song;

import java.util.Set;

public interface GenreService {

    Set<Genre> getGenres();

    Genre findById(Long id);

    GenreCommand findCommandById(Long id);

    GenreCommand saveGenreCommand(GenreCommand genreCommand);

    void deleteGenreById(Long id);

    Set<Song> getSongsByGenreId(Long id);

    void addSongToGenre(Long id, Long songId);
}
