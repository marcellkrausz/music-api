package com.codecool.musicapi.converters;


import com.codecool.musicapi.commands.PlaylistCommand;
import com.codecool.musicapi.model.Playlist;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PlaylistToPlaylistCommand implements Converter<Playlist, PlaylistCommand> {

    @Synchronized
    @Nullable
    @Override
    public PlaylistCommand convert(Playlist playlist) {
        if(playlist == null) {
            return null;
        }

        PlaylistCommand playlistCommand = new PlaylistCommand();
        playlistCommand.setId(playlist.getId());
        playlistCommand.setName(playlist.getName());

        return playlistCommand;
    }
}
