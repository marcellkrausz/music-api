package com.codecool.musicapi.controllers;


import com.codecool.musicapi.commands.ArtistCommand;
import com.codecool.musicapi.model.Artist;
import com.codecool.musicapi.services.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public Set<Artist> getAll() {
        return artistService.getArtists();
    }

    @GetMapping("/artists/{id}")
    public Artist getById(@PathVariable("id") Long id) {
        return artistService.findById(id);
    }

    @PostMapping("/artists")
    public void save(@RequestBody ArtistCommand artistCommand) {
        artistService.saveArtistCommand(artistCommand);
    }

    @PutMapping("/artists/{id}")
    public void update(@RequestBody ArtistCommand artistCommand, @PathVariable("id") Long id) {
        artistCommand.setId(id);
        artistService.saveArtistCommand(artistCommand);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArtist(@PathVariable("id") Long id) {
        artistService.deleteAlbumById(id);
    }
}
