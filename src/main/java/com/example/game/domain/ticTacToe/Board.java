package com.example.game.domain.ticTacToe;

import com.example.game.domain.isValid;
import com.example.game.helpers.ticTacToe.GamePrefConsts;
import lombok.Data;

@Data
public class Board implements isValid {

  private final int[][] board;
  private int currentState;
  private Player currentPlayer;
  private Move currentMove;

  protected Board(){
    this.board = new int[GamePrefConsts.BOARD_SIZE][GamePrefConsts.BOARD_SIZE];
    this.currentState = GamePrefConsts.PLAYING;
  }

  public static Board create(){
    Board board = new Board();
    for (int row = 0; row < GamePrefConsts.BOARD_SIZE; ++row) {
      for (int column = 0; column < GamePrefConsts.BOARD_SIZE; ++column){
        board.board[row][column] = GamePrefConsts.EMPTY;
      }
    }
    return board;
  }

  public Move takeTurn(Player player) {
    Move move = player.getMove();
    int row = move.getRow();
    int column = move.getColumn();
    if (row >= 0 && row < GamePrefConsts.BOARD_SIZE && column >= 0 && column < GamePrefConsts.BOARD_SIZE && board[row][column] == GamePrefConsts.EMPTY) {
      board[row][column] = player.getPlayer().getPlayerMark();
    } else {
      move = InvalidMove.create();
    }
    currentMove = move;
    return move;
  }

}
