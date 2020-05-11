package com.nikhilpb.dp;

public abstract class MarkovDecisionProcess {
  protected RewardFunction rewardFunction;
  protected double alpha;
  protected boolean isInfHorizon;

  public abstract State getBaseState();

  public abstract StateDistribution getDistribution(State state, Action action);

  public abstract void reset(long seed);

  public RewardFunction getRewardFunction() {
    return rewardFunction;
  }

  public double getAlpha() {
    return alpha;
  }
}
