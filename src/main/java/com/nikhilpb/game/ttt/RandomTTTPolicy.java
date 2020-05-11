package com.nikhilpb.game.ttt;

import com.nikhilpb.game.GamePolicy;
import lombok.val;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTTTPolicy implements GamePolicy<TickTacToeGame, TickTacToeAction> {

    @Override
    public Optional<TickTacToeAction> getAction(TickTacToeGame tickTacToeGame) {
        val actions = tickTacToeGame.getAllFeasibleActions();
        if (actions.isEmpty()) return Optional.empty();
        return Optional.of(actions.get(ThreadLocalRandom.current().nextInt(0, actions.size())));
    }
}
