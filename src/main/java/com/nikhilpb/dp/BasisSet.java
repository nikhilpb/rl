package com.nikhilpb.dp;

import java.util.ArrayList;

public class BasisSet {
  ArrayList<StateFunction> basis;

  public BasisSet() {
    basis = new ArrayList<StateFunction>();
  }

  public void add(StateFunction stateFunction) {
    basis.add(stateFunction);
  }

  public int size() {
    return basis.size();
  }

  public StateFunction get(int i) {
    return basis.get(i);
  }

  public double[] evaluate(State state) {
    double[] value = new double[basis.size()];
    for (int i = 0; i < basis.size(); ++ i) {
      value[i] = basis.get(i).value(state);
    }
    return value;
  }

  @Override
  public String toString() {
    String string = "";
    for (int i = 0; i < basis.size(); ++ i) {
      string += basis.get(i).toString() + "\n";
    }
    return string;
  }
}
