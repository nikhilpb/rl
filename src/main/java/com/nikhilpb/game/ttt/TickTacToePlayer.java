package com.nikhilpb.game.ttt;

import com.nikhilpb.game.Player;
import lombok.Data;
import lombok.NonNull;

@Data
public class TickTacToePlayer implements Player {
    private final String name;
    private final Character playerChar;

    public TickTacToePlayer(@NonNull String name, @NonNull Character playerChar) {
        this.name = name;
        this.playerChar = playerChar;
    }
}
