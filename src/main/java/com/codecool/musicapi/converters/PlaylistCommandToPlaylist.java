package com.codecool.musicapi.converters;

import com.codecool.musicapi.commands.PlaylistCommand;
import com.codecool.musicapi.model.Playlist;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PlaylistCommandToPlaylist implements Converter<PlaylistCommand, Playlist> {

    @Synchronized
    @Nullable
    @Override
    public Playlist convert(PlaylistCommand playlistCommand) {
        if(playlistCommand == null) {
            return null;
        }
        Playlist playlist = new Playlist();
        playlist.setId(playlistCommand.getId());
        playlist.setName(playlistCommand.getName());

        return playlist;
    }
}
