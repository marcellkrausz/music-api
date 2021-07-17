package com.codecool.musicapi.repositories;

import com.codecool.musicapi.model.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {
}
