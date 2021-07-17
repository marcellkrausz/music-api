package com.codecool.musicapi.services;

import com.codecool.musicapi.commands.GenreCommand;
import com.codecool.musicapi.converters.GenreCommandToGenre;
import com.codecool.musicapi.converters.GenreToGenreCommand;
import com.codecool.musicapi.model.Genre;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.repositories.GenreRepository;
import com.codecool.musicapi.repositories.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreCommandToGenre genreCommandToGenre;
    private final GenreToGenreCommand genreToGenreCommand;
    private final SongRepository songRepository;

    public GenreServiceImpl(GenreRepository genreRepository,
                            GenreCommandToGenre genreCommandToGenre,
                            GenreToGenreCommand genreToGenreCommand,
                            SongRepository songRepository) {
        this.genreRepository = genreRepository;
        this.genreCommandToGenre = genreCommandToGenre;
        this.genreToGenreCommand = genreToGenreCommand;
        this.songRepository = songRepository;
    }

    @Override
    public Set<Genre> getGenres() {
        Set<Genre> genres = new HashSet<>();
        genreRepository.findAll().iterator().forEachRemaining(genres::add);
        return genres;
    }

    @Override
    public Genre findById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);

        if (genreOptional.isEmpty()) {
            throw new RuntimeException("Genre is not found. Id: " + id);
        }
        return genreOptional.get();
    }

    @Override
    public GenreCommand findCommandById(Long id) {
        return genreToGenreCommand.convert(findById(id));
    }

    @Override
    public GenreCommand saveGenreCommand(GenreCommand genreCommand) {
        Genre detachedGenre = genreCommandToGenre.convert(genreCommand);

        Genre savedGenre = genreRepository.save(detachedGenre);
        log.debug("Saved Artist Id: " + savedGenre.getId());
        return genreToGenreCommand.convert(savedGenre);
    }

    @Override
    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Set<Song> getSongsByGenreId(@PathVariable("id") Long id) {
        Genre genre = findById(id);
        return genre.getSongs();
    }

    @Override
    public void addSongToGenre(Long id, Long songId) {
        Optional<Song> songOptional = songRepository.findById(songId);
        if (songOptional.isEmpty()) {
            throw new RuntimeException("Song not found");
        }
        Genre genre = findById(id);
        genre.addSong(songOptional.get());
        songOptional.get().getGenres().add(genre);
        songRepository.save(songOptional.get());
        genreRepository.save(genre);
    }
}
