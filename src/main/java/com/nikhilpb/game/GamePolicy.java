package com.nikhilpb.game;

import java.util.Optional;

public interface GamePolicy<G extends Game, A extends GameAction> {
    Optional<A> getAction(G g);
}
