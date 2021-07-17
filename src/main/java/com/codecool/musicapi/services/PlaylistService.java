package com.codecool.musicapi.services;

import com.codecool.musicapi.commands.PlaylistCommand;
import com.codecool.musicapi.model.Playlist;
import com.codecool.musicapi.model.Song;

import java.util.Set;


public interface PlaylistService {

    Set<Playlist> getPlaylists();

    Playlist findById(Long id);

    PlaylistCommand findCommandById(Long id);

    PlaylistCommand savePlaylistCommand(PlaylistCommand playlistCommand);

    void deletePlaylistById(Long id);

    Set<Song> getSongsByPlaylistId(Long id);

    void addSongToPlaylist(Long id, Long songId);
}
