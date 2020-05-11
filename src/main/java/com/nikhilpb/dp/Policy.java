package com.nikhilpb.dp;

public interface Policy {
  Action getAction(State state);
}
