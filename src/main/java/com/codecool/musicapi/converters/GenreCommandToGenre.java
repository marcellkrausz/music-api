package com.codecool.musicapi.converters;

import com.codecool.musicapi.commands.GenreCommand;
import com.codecool.musicapi.model.Genre;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class GenreCommandToGenre implements Converter<GenreCommand, Genre> {

    @Synchronized
    @Nullable
    @Override
    public Genre convert(GenreCommand genreCommand) {
        if (genreCommand == null) {
            return null;
        }

        final Genre genre = new Genre();
        genre.setId(genreCommand.getId());
        genre.setName(genreCommand.getName());

        return genre;
    }
}
