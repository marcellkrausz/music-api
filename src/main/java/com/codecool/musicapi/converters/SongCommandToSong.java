package com.codecool.musicapi.converters;

import com.codecool.musicapi.commands.SongCommand;
import com.codecool.musicapi.model.Album;
import com.codecool.musicapi.model.Song;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SongCommandToSong implements Converter<SongCommand, Song> {

    private final ArtistCommandToArtist artistConverter;
    private final GenreCommandToGenre genreConverter;

    public SongCommandToSong(ArtistCommandToArtist artistConverter, GenreCommandToGenre genreConverter) {
        this.artistConverter = artistConverter;
        this.genreConverter = genreConverter;
    }

    @Override
    public Song convert(SongCommand songCommand) {
        if (songCommand == null) {
            return null;
        }

        final Song song = new Song();
        song.setId(songCommand.getId());
        song.setName(songCommand.getName());

        if (songCommand.getAlbumId() != null) {
            Album album = new Album();
            album.setId(songCommand.getAlbumId());
            song.setAlbum(album);
            album.addSong(song);
        }

        if (songCommand.getArtists() != null && songCommand.getArtists().size() > 0) {
            songCommand.getArtists()
                    .forEach(artistCommand -> song.getArtists().add(artistConverter.convert(artistCommand)));
        }

        if (songCommand.getGenres() != null && songCommand.getGenres().size() > 0) {
            songCommand.getGenres()
                    .forEach(genreCommand -> song.getGenres().add(genreConverter.convert(genreCommand)));
        }

        return song;
    }
}
