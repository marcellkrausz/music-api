package com.codecool.musicapi.converters;



import com.codecool.musicapi.commands.ArtistCommand;
import com.codecool.musicapi.model.Artist;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ArtistCommandToArtist implements Converter<ArtistCommand, Artist> {

    @Synchronized
    @Nullable
    @Override
    public Artist convert(ArtistCommand artistCommand) {
        if(artistCommand == null) {
            return null;
        }

        final Artist artist = new Artist();
        artist.setId(artistCommand.getId());
        artist.setName(artistCommand.getName());

        return artist;
    }
}
