package com.codecool.musicapi.services;

import com.codecool.musicapi.commands.SongCommand;
import com.codecool.musicapi.model.Song;

import java.util.Set;

public interface SongService {

    Set<Song> getSongs();

    Song findById(Long id);

    SongCommand findCommandById(Long id);

    SongCommand saveSongCommand(SongCommand songCommand);

    void deleteSongById(Long id);
}
