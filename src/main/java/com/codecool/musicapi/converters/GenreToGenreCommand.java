package com.codecool.musicapi.converters;

import com.codecool.musicapi.commands.GenreCommand;
import com.codecool.musicapi.model.Genre;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GenreToGenreCommand implements Converter<Genre, GenreCommand> {

    @Synchronized
    @Nullable
    @Override
    public GenreCommand convert(Genre genre) {
        if (genre == null) {
            return null;
        }

        final GenreCommand genreCommand = new GenreCommand();
        genreCommand.setId(genre.getId());
        genreCommand.setName(genre.getName());

        return genreCommand;
    }
}
