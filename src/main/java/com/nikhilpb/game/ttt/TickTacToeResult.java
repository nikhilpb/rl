package com.nikhilpb.game.ttt;

import com.nikhilpb.game.FinalResult;
import com.nikhilpb.game.Player;

import java.util.Optional;


public class TickTacToeResult implements FinalResult<TickTacToePlayer> {

    private final Optional<TickTacToePlayer> winner;

    public TickTacToeResult(Optional<TickTacToePlayer> winner) {
        this.winner = winner;
    }

    @Override
    public Optional<TickTacToePlayer> winner() {
        return winner;
    }

    @Override
    public String textResult() {
        if (!winner.isPresent()) return "Game ended in a draw";
        return winner.get().toString() + " won";
    }

    @Override
    public float getScore(TickTacToePlayer tickTacToePlayer) {
        if (!winner.isPresent()) return 0.0f;
        return  winner.get() == tickTacToePlayer ? 1.0f : -1.0f;
    }

    @Override
    public String toString() {
        if (!winner.isPresent()) {
            return "No winner";
        } else {
            return "Winner is: " + winner.get().toString();
        }
    }
}
