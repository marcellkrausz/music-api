package com.codecool.musicapi.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlbumCommand {

    private Long id;
    private String name;
    private Long artistId;
}
