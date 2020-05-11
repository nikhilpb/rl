package com.nikhilpb.game;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.Optional;

@Log
public class GameSimulator {
    @Getter
    private final Game game;
    private final HashMap<Player, GamePolicy> policies;

    public GameSimulator(@NonNull Game game,@NonNull HashMap<Player, GamePolicy> policies) {
        if (game.numPlayers() != policies.size()) throw new IllegalArgumentException("number of players don't match");
        this.game = game;
        this.policies = policies;
    }

    public Optional<FinalResult> simulateSteps(int numSteps, boolean printStates) {
        int step = 1;
        while (numSteps > 0) {
            if (printStates) {
                System.out.println("Step no: " + step);
                System.out.println(game.prettyPrint());
            }
            step++;
            Optional<FinalResult> resultOr = game.isGameOver();
            if (resultOr.isPresent()) {
                if (printStates) {
                    System.out.println("Result: " + resultOr.get().toString());
                }
                return resultOr;
            }
            numSteps--;
            Player p = game.getCurrentPlayer();
            if (printStates) {
                System.out.println("Current player: " + p);
            }
            if (!policies.containsKey(p)) throw new IllegalArgumentException("unrecognized player found");
            GamePolicy pol = policies.get(p);
            Optional<GameAction> actionOr = pol.getAction(game);
            if (!actionOr.isPresent()) {
                log.info("Policy did not return any actions.");
                return Optional.empty();
            }
            GameAction action = actionOr.get();
            if (!game.actionValid(action)) {
                log.info("Policy gives an invalid action: ." + action.toString());
                return Optional.empty();
            }
            if (printStates) {
                System.out.println("Action chosen: " + action);
            }
            game.playAction(action);
        }
        Optional<FinalResult> resultOr = game.isGameOver();
        if (resultOr.isPresent()) {
            log.info("Result: " + resultOr.get().toString());
            return resultOr;
        }
        return Optional.empty();
    }
}
