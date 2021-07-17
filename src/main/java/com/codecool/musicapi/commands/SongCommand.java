package com.codecool.musicapi.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SongCommand {
    private Long id;
    private String name;
    private Long albumId;
    private Set<ArtistCommand> artists = new HashSet<>();
    private Set<GenreCommand> genres = new HashSet<>();
}
