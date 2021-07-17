package com.codecool.musicapi.repositories;

import com.codecool.musicapi.model.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
}
