package com.nikhilpb.game.ttt;

import com.nikhilpb.game.Game;
import lombok.NonNull;
import lombok.extern.java.Log;
import lombok.val;

import java.util.ArrayList;
import java.util.Optional;

@Log
public class TickTacToeGame implements Game<TickTacToePlayer, TickTacToeAction, TickTacToeResult> {

    private final TickTacToePlayer firstPlayer;
    private final TickTacToePlayer secondPlayer;
    private ArrayList<TickTacToePlayer> players;
    private boolean firstPlayerTurn = true;
    private char[][] state;
    private Optional<TickTacToeResult> result;

    private int[][][] ranges = {
            {{0,0}, {1, 1}, {2, 2},},
            {{0,2}, {1, 1}, {2, 0},},
            {{0,0}, {0, 1}, {0, 2},},
            {{1,0}, {1, 1}, {1, 2},},
            {{2,0}, {2, 1}, {2, 2},},
            {{0,0}, {1, 0}, {2, 0},},
            {{0,1}, {1, 1}, {2, 1},},
            {{0,2}, {1, 2}, {2, 2},},
    };

    public TickTacToeGame(@NonNull String firstPlayer, @NonNull String secondPlayer) {
        this.firstPlayer = new TickTacToePlayer(firstPlayer, 'X');
        this.secondPlayer = new TickTacToePlayer(secondPlayer, 'O');
        this.state = new char[3][3];
        this.result = Optional.empty();
        players = new ArrayList<>();
        players.add(this.firstPlayer);
        players.add(this.secondPlayer);
    }

    private TickTacToeGame(TickTacToeGame other) {
        this.firstPlayer = other.firstPlayer;
        this.secondPlayer = other.secondPlayer;
        this.result = other.result;
        players = new ArrayList<>();
        players.add(this.firstPlayer);
        players.add(this.secondPlayer);
        this.firstPlayerTurn = other.firstPlayerTurn;

        this.state = new char[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.state[i][j] = other.state[i][j];
            }
        }
    }

    boolean isEmpty(char c) {
        return c != 'X' && c != 'O';
    }

    TickTacToePlayer playerForChar(char c) {
        return c == 'X' ? firstPlayer : secondPlayer;
    }

    @Override
    public int numPlayers() {
        return 2;
    }

    @Override
    public TickTacToePlayer getCurrentPlayer() {
        return firstPlayerTurn ? firstPlayer : secondPlayer;
    }

    @Override
    public boolean playAction(TickTacToeAction action) {
        if (!actionValid(action) || result.isPresent()) return false;
        state[action.getRow()][action.getColumn()] = firstPlayerTurn ? firstPlayer.getPlayerChar() : secondPlayer.getPlayerChar();
        firstPlayerTurn = !firstPlayerTurn;
        result = isGameOver();
        return true;
    }

    @Override
    public boolean actionValid(TickTacToeAction action) {
        return isEmpty(state[action.getRow()][action.getColumn()]);
    }

    @Override
    public Optional<TickTacToeResult> isGameOver() {
        for (val r : ranges) {
            char firstCar = '$';
            int count = 0;
            boolean firstOne = true;
            for (val p : r) {
                if (isEmpty(state[p[0]][p[1]])) break;
                if (firstOne) {
                    firstCar = state[p[0]][p[1]];
                    firstOne = false;
                    count++;
                } else {
                    if (firstCar == state[p[0]][p[1]]) {
                        count++;
                    }
                }
            }
            if (count == 3) return Optional.of(new TickTacToeResult(Optional.of(playerForChar(firstCar))));
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<TickTacToePlayer> getAllPlayers() {
        return players;
    }

    @Override
    public ArrayList<TickTacToeAction> getAllFeasibleActions() {
        ArrayList<TickTacToeAction> actions = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (state[i][j] != 'X' && state[i][j] != 'O') {
                    actions.add(new TickTacToeAction(i, j));
                }
            }
        }
        return actions;
    }

    @Override
    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        for (char[] r : state) {
            for (char c : r) {
                builder.append(c);
                builder.append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    @Override
    public TickTacToeGame copy() {
        return new TickTacToeGame(this);
    }
}
