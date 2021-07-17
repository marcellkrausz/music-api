package com.codecool.musicapi.controllers;


import com.codecool.musicapi.commands.PlaylistCommand;
import com.codecool.musicapi.model.Playlist;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.services.PlaylistService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlists")
    public Set<Playlist> getAll() {
        return playlistService.getPlaylists();
    }

    @GetMapping("/playlists/{id}")
    public Playlist getById(@PathVariable("id") Long id) {
        return playlistService.findById(id);
    }

    @PostMapping("/playlists")
    public void save(@RequestBody PlaylistCommand playlistCommand) {
        playlistService.savePlaylistCommand(playlistCommand);
    }

    @PutMapping("/playlists/{id}")
    public void update(@RequestBody PlaylistCommand playlistCommand, @PathVariable("id") Long id) {
        playlistCommand.setId(id);
        playlistService.savePlaylistCommand(playlistCommand);
    }

    @DeleteMapping("/playlist/{id}")
    public void deletePlaylist(@PathVariable("id") Long id) {
        playlistService.deletePlaylistById(id);
    }

    @GetMapping("/playlist/{id}/songs")
    public Set<Song> getSongsByPlaylistId(@PathVariable Long id) {
        return playlistService.getSongsByPlaylistId(id);
    }

    @PostMapping("/playlists/{id}/songs/{songId}")
    public void addSongToPlaylist(@PathVariable("id") Long id, @PathVariable("songId") Long songId) {
        playlistService.addSongToPlaylist(id,songId);
    }
}
