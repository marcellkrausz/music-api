package com.codecool.musicapi.converters;


import com.codecool.musicapi.commands.ArtistCommand;
import com.codecool.musicapi.model.Artist;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ArtistToArtistCommand implements Converter<Artist, ArtistCommand> {

    @Synchronized
    @Nullable
    @Override
    public ArtistCommand convert(Artist artist) {
        if (artist == null) {
            return null;
        }

        final ArtistCommand artistCommand = new ArtistCommand();
        artistCommand.setId(artist.getId());
        artistCommand.setName(artist.getName());

        return artistCommand;
    }
}
