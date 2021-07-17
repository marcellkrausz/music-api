package com.codecool.musicapi.converters;


import com.codecool.musicapi.commands.AlbumCommand;
import com.codecool.musicapi.model.Album;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AlbumToAlbumCommand implements Converter<Album, AlbumCommand> {

    @Synchronized
    @Nullable
    @Override
    public AlbumCommand convert(Album album) {
        if (album == null) {
            return null;
        }

        final AlbumCommand albumCommand = new AlbumCommand();
        albumCommand.setId(album.getId());
        albumCommand.setName(album.getName());

        if(album.getArtist() != null) {
            albumCommand.setArtistId(album.getArtist().getId());
        }

        return albumCommand;
    }
}
