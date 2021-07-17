package com.codecool.musicapi.services;


import com.codecool.musicapi.commands.AlbumCommand;
import com.codecool.musicapi.converters.AlbumCommandToAlbum;
import com.codecool.musicapi.converters.AlbumToAlbumCommand;
import com.codecool.musicapi.model.Album;
import com.codecool.musicapi.model.Song;
import com.codecool.musicapi.repositories.AlbumRepository;
import com.codecool.musicapi.repositories.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumCommandToAlbum albumCommandToAlbum;
    private final AlbumToAlbumCommand albumToAlbumCommand;
    private final SongRepository songRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository,
                            AlbumCommandToAlbum albumCommandToAlbum,
                            AlbumToAlbumCommand albumToAlbumCommand,
                            SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.albumCommandToAlbum = albumCommandToAlbum;
        this.albumToAlbumCommand = albumToAlbumCommand;
        this.songRepository = songRepository;
    }

    @Override
    public Set<Album> getAlbum() {
        Set<Album> albumSet = new HashSet<>();
        albumRepository.findAll().iterator().forEachRemaining(albumSet::add);
        return albumSet;
    }

    @Override
    public Album findById(Long id) {
        Optional<Album> albumOptional = albumRepository.findById(id);

        if (albumOptional.isEmpty()) {
            throw new RuntimeException("Album not found. For ID value: " + id.toString());
        }
        return albumOptional.get();
    }

    @Override
    public AlbumCommand findCommandById(Long id) {
        return albumToAlbumCommand.convert(findById(id));
    }

    @Override
    public AlbumCommand saveAlbumCommand(AlbumCommand albumCommand) {
        Album detachedAlbum = albumCommandToAlbum.convert(albumCommand);

        Album savedAlbum = albumRepository.save(detachedAlbum);
        log.debug("Saved Album Id: " + savedAlbum.getId());
        return albumToAlbumCommand.convert(savedAlbum);
    }

    @Override
    public void deleteAlbumById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Set<Song> getSongsByAlbumId(Long id) {
        Album albums = findById(id);
        Set<Song> songs = albums.getSongs();
        return songs;
    }

    @Override
    public void addSongToAlbumById(Long id, Long songId) {
        Optional<Song> songOptional = songRepository.findById(songId);
        if (songOptional.isEmpty()) {
            throw new RuntimeException("Song not found");
        }
        Album album = findById(id);
        album.getSongs().add(songOptional.get());
        songOptional.get().getAlbum().addSong(songOptional.get());
        songRepository.save(songOptional.get());
        albumRepository.save(album);
    }
}
