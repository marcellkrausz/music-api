package com.codecool.musicapi.services;


import com.codecool.musicapi.commands.ArtistCommand;
import com.codecool.musicapi.model.Artist;

import java.util.Set;

public interface ArtistService {

    Set<Artist> getArtists();

    Artist findById(Long id);

    ArtistCommand findCommandById(Long id);

    ArtistCommand saveArtistCommand(ArtistCommand artistCommand);

    void deleteAlbumById(Long id);
}
