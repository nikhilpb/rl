package com.nikhilpb.dp;

public class ConstantStateFunction implements StateFunction {
  private final double val;

  public ConstantStateFunction(double val) {
    this.val = val;
  }

  @Override
  public double value(State state) {
    return val;
  }

  @Override
  public String toString() {
    return "const_" + val;
  }
}
