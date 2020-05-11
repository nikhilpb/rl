package com.nikhilpb.game.ttt;

import com.nikhilpb.game.GamePolicy;

import java.util.Optional;

public class InOrderTTTPolicy implements GamePolicy<TickTacToeGame, TickTacToeAction> {
    @Override
    public Optional<TickTacToeAction> getAction(TickTacToeGame game) {
        for (int r = 0; r < 3; ++r) {
            for (int c = 0; c < 3; ++c) {
                TickTacToeAction action = new TickTacToeAction(r, c);
                if (game.actionValid(action)) return Optional.of(action);
            }
        }
        return Optional.empty();
    }
}
