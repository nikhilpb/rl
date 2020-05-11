package com.nikhilpb.dp;

import java.util.ArrayList;

public class QFunctionPolicy implements Policy {
  private MarkovDecisionProcess model;

  public QFunction getqFunction() {
    return qFunction;
  }

  private QFunction qFunction;
  private RewardFunction rewardFunction;
  private double alpha;

  public QFunctionPolicy(MarkovDecisionProcess model,
                         QFunction qFunction,
                         RewardFunction rewardFunction,
                         double alpha) {
    this.model = model;
    this.qFunction = qFunction;
    this.rewardFunction = rewardFunction;
    this.alpha = alpha;
  }

  /**
   * @param state
   * @return Action corresponding to the state given by the policy.
   */
  @Override
  public Action getAction(State state) {
    ArrayList<Action> actions = state.getActions();
    Action maxAction = null;
    double value, maxValue = - Double.MAX_VALUE;
    for (Action a : actions) {
      double rfv = rewardFunction.value(state, a);
      double qfv = qFunction.value(state, a);
      value = rfv + alpha * qfv;
      if (value > maxValue) {
        maxValue = value;
        maxAction = a;
      }
    }
    return maxAction;
  }
}
