package com.nikhilpb.game.ttt;

import com.nikhilpb.game.GameAction;
import lombok.Data;

@Data
public class TickTacToeAction implements GameAction {
    private final int row;
    private final int column;


    public TickTacToeAction(int row, int column) {
        if (row < 0 || row > 2 || column < 0 || column > 2) throw new IllegalArgumentException("Row and/or column out of bounds");
        this.row = row;
        this.column = column;
    }
}
