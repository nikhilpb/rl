package com.nikhilpb.dp;

public class StateAction {
  private final State state;
  private final Action action;

  public StateAction(State state, Action action) {
    this.state = state;
    this.action = action;
  }

  public State getState() {
    return state;
  }

  public Action getAction() {
    return action;
  }
}
