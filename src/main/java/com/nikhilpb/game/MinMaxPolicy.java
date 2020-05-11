package com.nikhilpb.game;
import lombok.extern.java.Log;
import lombok.val;

import java.util.Optional;

@Log
public class MinMaxPolicy implements GamePolicy {

    private float getScore(Game game, GameAction action, Player originalPlayer) {
        if(!game.playAction(action)) return 0;
        val resultOr = game.isGameOver();
        if (resultOr.isPresent()) {
           val result = (FinalResult) resultOr.get();
           return result.getScore(originalPlayer);
        }
        if(game.getAllFeasibleActions().isEmpty()) return 0.0f;
        float min = 1.0f;
        float max = -1.0f;
        for (Object o : game.getAllFeasibleActions()) {
            final float score = getScore(game.copy(), (GameAction) o, originalPlayer);
            min = Float.min(score, min);
            max = Float.max(score, max);
        }
        return game.getCurrentPlayer() == originalPlayer ? max : min;
    }

    @Override
    public Optional<GameAction> getAction(Game game) {
        if (game.isGameOver().isPresent()) return Optional.empty();
        Player p = game.getCurrentPlayer();
        float maxScore = -10.0f;
        GameAction optimalAction = null;
        for (Object o : game.getAllFeasibleActions()) {
            GameAction a = (GameAction) o;
            float score = getScore(game.copy(), a, p);
            if (score > maxScore) {
                optimalAction = a;
                maxScore = score;
            }
        }
        if (optimalAction == null) return Optional.empty();
        return Optional.of(optimalAction);
    }
}
