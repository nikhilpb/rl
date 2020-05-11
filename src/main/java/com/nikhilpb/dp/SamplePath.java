package com.nikhilpb.dp;

import java.util.ArrayList;

public class SamplePath {
  public ArrayList<StateAction> stateActions;
  public double reward;

  public SamplePath() {
    stateActions = new ArrayList<StateAction>();
    reward = 0.0;
  }

  @Override
  public String toString() {
    String string = "";
    for (StateAction sa : stateActions) {
      string += "state : " + sa.getState().toString()
                        + "\naction : " + sa.getAction().toString() + "\n";
    }
    string += "reward: " + reward + "\n";
    return string;
  }
}
