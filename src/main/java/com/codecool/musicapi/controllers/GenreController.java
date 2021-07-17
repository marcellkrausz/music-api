package com.codecool.musicapi.controllers;

import com.codecool.musicapi.commands.GenreCommand;
import com.codecool.musicapi.model.Genre;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.services.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public Set<Genre> getAll() {
        return genreService.getGenres();
    }

    @GetMapping("/genres/{id}")
    public Genre getById(@PathVariable("id") Long id) {
        return genreService.findById(id);
    }

    @PostMapping("/genres")
    public void save(@RequestBody GenreCommand genreCommand) {
        genreService.saveGenreCommand(genreCommand);
    }

    @PutMapping("/genres/{id}")
    public void update (@RequestBody GenreCommand genreCommand, @PathVariable("id")Long id) {
        genreCommand.setId(id);
        genreService.saveGenreCommand(genreCommand);
    }

    @DeleteMapping("/genres/{id}")
    public void deleteGenre(@PathVariable("id")Long id) {
        genreService.deleteGenreById(id);
    }

    @GetMapping("/genres/{id}/songs")
    public Set<Song> getSongsByGenreId(@PathVariable("id") Long id) {
        return genreService.getSongsByGenreId(id);
    }

    @PostMapping("/genres/{id}/songs/{songId}")
    public void addSongToGenre(@PathVariable("id") Long id, @PathVariable("songId") Long songId) {
         genreService.addSongToGenre(id, songId);
    }
}
