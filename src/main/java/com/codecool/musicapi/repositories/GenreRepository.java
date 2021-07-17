package com.codecool.musicapi.repositories;

import com.codecool.musicapi.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
