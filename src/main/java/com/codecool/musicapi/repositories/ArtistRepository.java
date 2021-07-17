package com.codecool.musicapi.repositories;

import com.codecool.musicapi.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
