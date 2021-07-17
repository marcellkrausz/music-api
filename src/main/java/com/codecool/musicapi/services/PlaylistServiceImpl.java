package com.codecool.musicapi.services;

import com.codecool.musicapi.commands.PlaylistCommand;
import com.codecool.musicapi.converters.PlaylistCommandToPlaylist;
import com.codecool.musicapi.converters.PlaylistToPlaylistCommand;
import com.codecool.musicapi.model.Playlist;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.repositories.PlaylistRepository;
import com.codecool.musicapi.repositories.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistToPlaylistCommand playlistToPlaylistCommand;
    private final PlaylistCommandToPlaylist playlistCommandToPlaylist;
    private final SongRepository songRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               PlaylistToPlaylistCommand playlistToPlaylistCommand,
                               PlaylistCommandToPlaylist playlistCommandToPlaylist,
                               SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.playlistToPlaylistCommand = playlistToPlaylistCommand;
        this.playlistCommandToPlaylist = playlistCommandToPlaylist;
        this.songRepository = songRepository;
    }

    @Override
    public Set<Playlist> getPlaylists() {
        Set<Playlist> playlists = new HashSet<>();
        playlistRepository.findAll().iterator().forEachRemaining(playlists::add);
        return playlists;
    }

    @Override
    public Playlist findById(Long id) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(id);

        if(playlistOptional.isEmpty()) {
            throw new RuntimeException("Playlist not found. Id: " + id);
        }
        return playlistOptional.get();
    }

    @Override
    public PlaylistCommand findCommandById(Long id) {
        return playlistToPlaylistCommand.convert(findById(id));
    }

    @Override
    public PlaylistCommand savePlaylistCommand(PlaylistCommand playlistCommand) {
        Playlist detachedPlaylist = playlistCommandToPlaylist.convert(playlistCommand);

        Playlist savedPlaylist = playlistRepository.save(detachedPlaylist);
        log.debug("Saved playlist id: " + savedPlaylist.getId());
        return playlistToPlaylistCommand.convert(savedPlaylist);
    }

    @Override
    public void deletePlaylistById(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Set<Song> getSongsByPlaylistId(Long id) {
        Playlist playlists = findById(id);
        return playlists.getSongs();
    }

    @Override
    public void addSongToPlaylist(Long id, Long songId) {
        Optional<Song> songOptional = songRepository.findById(songId);
        if (songOptional.isEmpty()) {
            throw new RuntimeException("Song not found");
        }
        Playlist playlist = findById(id);
        playlist.getSongs().add(songOptional.get());
        songOptional.get().getPlaylists().add(playlist);
        playlistRepository.save(playlist);
        songRepository.save(songOptional.get());
    }
}
