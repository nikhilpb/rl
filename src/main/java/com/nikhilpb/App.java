package com.nikhilpb;

import com.nikhilpb.game.GamePolicy;
import com.nikhilpb.game.GameSimulator;
import com.nikhilpb.game.MinMaxPolicy;
import com.nikhilpb.game.Player;
import com.nikhilpb.game.ttt.RandomTTTPolicy;
import com.nikhilpb.game.ttt.TickTacToeGame;
import com.nikhilpb.game.ttt.TickTacToePlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main( String[] args ) {
        TickTacToeGame game = new TickTacToeGame("Nikhil", "Kritika");
        ArrayList<TickTacToePlayer> players = game.getAllPlayers();
        HashMap<Player, GamePolicy> policies = new HashMap<>();
        policies.put(players.get(0), new RandomTTTPolicy());
        policies.put(players.get(1), new MinMaxPolicy());
        GameSimulator simulator = new GameSimulator(game, policies);
        simulator.simulateSteps(100, true);
    }
}