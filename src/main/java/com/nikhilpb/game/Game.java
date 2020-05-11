package com.nikhilpb.game;

import java.util.ArrayList;
import java.util.Optional;

public interface Game<P extends Player, A extends GameAction, R extends FinalResult> {
    int numPlayers();

    P getCurrentPlayer();

    boolean playAction(A action);

    boolean actionValid(A action);

    Optional<R> isGameOver();

    ArrayList<P> getAllPlayers();

    ArrayList<A> getAllFeasibleActions();

    String prettyPrint();

    Game copy();
}
