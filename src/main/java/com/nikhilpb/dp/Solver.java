package com.nikhilpb.dp;

public interface Solver {
  boolean solve() throws Exception;

  Policy getPolicy();
}
