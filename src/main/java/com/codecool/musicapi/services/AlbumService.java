package com.codecool.musicapi.services;



import com.codecool.musicapi.commands.AlbumCommand;
import com.codecool.musicapi.model.Album;
import com.codecool.musicapi.model.Song;

import java.util.Set;

public interface AlbumService {

    Set<Album> getAlbum();

    Album findById(Long id);

    AlbumCommand findCommandById(Long id);

    AlbumCommand saveAlbumCommand(AlbumCommand albumCommand);

    void deleteAlbumById(Long id);

    Set<Song> getSongsByAlbumId(Long id);

    void addSongToAlbumById(Long id, Long songId);
}
