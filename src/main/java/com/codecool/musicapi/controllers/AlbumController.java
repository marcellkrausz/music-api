package com.codecool.musicapi.controllers;


import com.codecool.musicapi.commands.AlbumCommand;
import com.codecool.musicapi.model.Album;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.services.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public Set<Album> getAll() {
        return albumService.getAlbum();
    }

    @GetMapping("/albums/{id}")
    public Album getById(@PathVariable("id") Long id) {
        return albumService.findById(id);
    }

    @PostMapping("/albums")
    public void save(@RequestBody AlbumCommand albumCommand) {
        albumService.saveAlbumCommand(albumCommand);
    }

    @PutMapping("/albums/{id}")
    public void update(@RequestBody AlbumCommand albumCommand, @PathVariable("id") Long id) {
        albumCommand.setId(id);
        albumService.saveAlbumCommand(albumCommand);
    }

    @DeleteMapping("/album")
    public void deleteAlbum(@PathVariable("id") Long id) {
        albumService.deleteAlbumById(id);
    }

    @GetMapping("albums/{id}/songs")
    public Set<Song> getSongsByAlbumId(@PathVariable("id") Long id) {
        return albumService.getSongsByAlbumId(id);
    }

    @PostMapping("/albums/{id}/songs/{songId}")
    public void addSongToAlbumById(@PathVariable("id") Long id, @PathVariable("songId") Long songId) {
        albumService.addSongToAlbumById(id, songId);
    }
}
