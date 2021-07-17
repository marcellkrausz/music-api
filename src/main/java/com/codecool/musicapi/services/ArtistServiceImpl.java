package com.codecool.musicapi.services;

import com.codecool.musicapi.commands.ArtistCommand;
import com.codecool.musicapi.converters.ArtistCommandToArtist;
import com.codecool.musicapi.converters.ArtistToArtistCommand;
import com.codecool.musicapi.model.Artist;
import com.codecool.musicapi.repositories.ArtistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistRepository artistRepository;
    private ArtistToArtistCommand artistToArtistCommand;
    private ArtistCommandToArtist artistCommandToArtist;

    public ArtistServiceImpl(ArtistRepository artistRepository,
                             ArtistToArtistCommand artistToArtistCommand,
                             ArtistCommandToArtist artistCommandToArtist) {
        this.artistRepository = artistRepository;
        this.artistToArtistCommand = artistToArtistCommand;
        this.artistCommandToArtist = artistCommandToArtist;
    }

    @Override
    public Set<Artist> getArtists() {
        Set<Artist> artists = new HashSet<>();
        artistRepository.findAll().iterator().forEachRemaining(artists::add);
        return artists;
    }

    @Override
    public Artist findById(Long id) {
        Optional<Artist> artistOptional = artistRepository.findById(id);

        if(artistOptional.isEmpty()) {
            throw new RuntimeException("Artist not found. For ID value: " + id.toString());
        }
        return artistOptional.get();
    }

    @Override
    public ArtistCommand findCommandById(Long id) {
        return artistToArtistCommand.convert(findById(id));
    }

    @Override
    public ArtistCommand saveArtistCommand(ArtistCommand artistCommand) {
        Artist detachedArtist = artistCommandToArtist.convert(artistCommand);

        Artist savedArtist = artistRepository.save(detachedArtist);
        log.debug("Saved Artist Id: " + savedArtist.getId());
        return artistToArtistCommand.convert(savedArtist);
    }

    @Override
    public void deleteAlbumById(Long id) {
        artistRepository.deleteById(id);
        log.info("Artist deleted: " + id);
    }
}
