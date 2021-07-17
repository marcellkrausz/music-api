package com.codecool.musicapi.converters;

import com.codecool.musicapi.commands.SongCommand;
import com.codecool.musicapi.model.Song;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SongToSongCommand implements Converter<Song, SongCommand> {

    private final ArtistToArtistCommand artistToArtistCommand;
    private final GenreToGenreCommand genreConverter;

    public SongToSongCommand(ArtistToArtistCommand artistToArtistCommand, GenreToGenreCommand genreConverter) {
        this.artistToArtistCommand = artistToArtistCommand;
        this.genreConverter = genreConverter;
    }

    @Override
    public SongCommand convert(Song song) {
        if(song == null) {
            return null;
        }
        SongCommand songCommand = new SongCommand();
        songCommand.setId(song.getId());
        songCommand.setName(song.getName());

        if(song.getAlbum() != null) {
            songCommand.setAlbumId(song.getAlbum().getId());
        }

        if (song.getArtists() != null && song.getArtists().size() > 0) {
            song.getArtists()
                    .forEach(artist -> songCommand.getArtists().add(artistToArtistCommand.convert(artist)));
        }

        if(song.getGenres() != null && song.getGenres().size() > 0) {
            song.getGenres()
                    .forEach(genre -> songCommand.getGenres().add(genreConverter.convert(genre)));
        }
        return songCommand;
    }
}
