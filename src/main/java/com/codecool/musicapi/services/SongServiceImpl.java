package com.codecool.musicapi.services;

import com.codecool.musicapi.commands.SongCommand;
import com.codecool.musicapi.converters.SongCommandToSong;
import com.codecool.musicapi.converters.SongToSongCommand;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.repositories.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongToSongCommand songToSongCommand;
    private final SongCommandToSong songCommandToSong;

    public SongServiceImpl(SongRepository songRepository,
                           SongToSongCommand songToSongCommand,
                           SongCommandToSong songCommandToSong) {
        this.songRepository = songRepository;
        this.songToSongCommand = songToSongCommand;
        this.songCommandToSong = songCommandToSong;
    }

    @Override
    public Set<Song> getSongs() {
        Set<Song> songs = new HashSet<>();
        songRepository.findAll().iterator().forEachRemaining(songs::add);
        return songs;
    }

    @Override
    public Song findById(Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);

        if(optionalSong.isEmpty()) {
            throw new RuntimeException("Song not found. Id: " + id);
        }
        return optionalSong.get();
    }

    @Override
    public SongCommand findCommandById(Long id) {
        return songToSongCommand.convert(findById(id));
    }

    @Override
    public SongCommand saveSongCommand(SongCommand songCommand) {
        Song detachedSong = songCommandToSong.convert(songCommand);

        Song savedSong = songRepository.save(detachedSong);
        log.debug("Saved Song Id: " + savedSong.getId());
        return songToSongCommand.convert(savedSong);
    }

    @Override
    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }
}
