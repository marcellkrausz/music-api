package com.codecool.musicapi.converters;

import com.codecool.musicapi.commands.AlbumCommand;
import com.codecool.musicapi.model.Album;
import com.codecool.musicapi.model.Artist;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AlbumCommandToAlbum implements Converter<AlbumCommand, Album> {

    @Synchronized
    @Nullable
    @Override
    public Album convert(AlbumCommand albumCommand) {
        if (albumCommand == null) {
            return null;
        }

        final Album album = new Album();
        album.setId(albumCommand.getId());
        album.setName(albumCommand.getName());

        if (albumCommand.getArtistId() != null) {
            Artist artist = new Artist();
            artist.setId(albumCommand.getArtistId());
            album.setArtist(artist);
            artist.addAlbum(album);
        }

        return album;
    }
}
