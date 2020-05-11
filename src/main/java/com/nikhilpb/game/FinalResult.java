package com.nikhilpb.game;

import java.util.Optional;

public interface FinalResult<P extends Player> {
    Optional<P> winner();

    String textResult();

    float getScore(P p);
}
