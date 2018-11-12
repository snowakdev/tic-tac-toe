package com.example.game.helpers.ticTacToe;

public class GamePrefConsts {
  public static final int BOARD_SIZE = 3;
  public static final int EMPTY = 0;
  public static final int CROSS = 1;
  public static final int NOUGHT = 2;

  public static final int PLAYING = 0;
  public static final int CROSS_PLAYER_WON = 1;
  public static final int NOUGHT_PLAYER_WON = 2;
  public static final int DRAW = 3;
  public static final int INVALID = 4;

  public static final String WELCOME_MESSAGE = "Welcome to Tic Tac Toe Game\n";
  public static final String PLAYER_CHOICE = "Please choose the first player: 'x' or 'o'";
  public static final String INVALID_MOVE = "This move at (%s, %s) is not valid. Try again...)";
  public static final String GOODBYE = "Bye!.";
  public static final String PLAYER_TURN = "Player '%s', enter your move (row[1-3] column[1-3]): ";
  public static final String PLAYER_WON = "Player '%s' won! Bye!";
  public static final String GAME_DRAW = "It's a Draw! Bye!";
}
