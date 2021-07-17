package com.codecool.musicapi.repositories;

import com.codecool.musicapi.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {
}
