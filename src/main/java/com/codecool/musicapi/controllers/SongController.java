package com.codecool.musicapi.controllers;

import com.codecool.musicapi.commands.SongCommand;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.services.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs")
    public Set<Song> getAll() {
        return songService.getSongs();
    }

    @GetMapping("/songs/{id}")
    public Song getById(@PathVariable("id")Long id) {
        return songService.findById(id);
    }

    @PostMapping("/songs")
    public void save(@RequestBody SongCommand songCommand) {
        songService.saveSongCommand(songCommand);
    }

    @PutMapping("/songs/{id}")
    public void update(@RequestBody SongCommand songCommand, @PathVariable("id") Long id) {
        songCommand.setId(id);
        songService.saveSongCommand(songCommand);
    }

    @DeleteMapping("/songs/{id}")
    public void deleteSong(@PathVariable("id") Long id) {
        songService.deleteSongById(id);
    }
}
