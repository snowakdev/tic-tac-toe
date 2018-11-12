package com.example.game.repository.ticTacToe;

import com.example.game.domain.ticTacToe.Player;
import com.example.game.service.OutputService;
import com.example.game.domain.ticTacToe.Move;
import com.example.game.domain.ticTacToe.Board;
import com.example.game.helpers.ticTacToe.GamePrefConsts;


public class ConsolePrintService implements OutputService {

  @Override
  public void print2dBoard(Board board) {
    for (int row = 0; row < GamePrefConsts.BOARD_SIZE; ++row) {
      for (int column = 0; column < GamePrefConsts.BOARD_SIZE; ++column){
        printCell(board.getBoard()[row][column]);
        if( column != GamePrefConsts.BOARD_SIZE - 1 ) {
          printPipe();
        }
      }
      System.out.println();
      if( row != GamePrefConsts.BOARD_SIZE - 1 ) {
        printHorizontalLine();
      }
    }
    System.out.println();
  }

  @Override
  public void printPlayerChoice() {
    System.out.println(GamePrefConsts.PLAYER_CHOICE);
  }

  @Override
  public void printPlayerTurn(Player player) {
    System.out.println(String.format(GamePrefConsts.PLAYER_TURN, player.getPlayer().getPlayerName()));
  }

  @Override
  public void printInvalidMove(Move move) {
    System.out.println(String.format(GamePrefConsts.INVALID_MOVE, move.getRow(), move.getColumn()));
  }

  @Override
  public void printWinner(Player player) {
    System.out.println(String.format(GamePrefConsts.PLAYER_WON, player.getPlayer().getPlayerName()));
  }

  @Override
  public void printDrawResult() {
    System.out.println(GamePrefConsts.GAME_DRAW);
  }

  @Override
  public void printWelcomeMessage() {
    System.out.println(GamePrefConsts.WELCOME_MESSAGE);
  }

  @Override
  public void printGoodbyeMessage() {
    System.out.println(GamePrefConsts.GOODBYE);
  }

  private void printCell(int content) {
    switch (content) {
      case GamePrefConsts.CROSS:
        System.out.print("X"); break;
      case GamePrefConsts.NOUGHT:
        System.out.print("O"); break;
      default:
        System.out.print(" "); break;
    }
  }

  private void printPipe() {
    System.out.print("|");
  }

  private void printHorizontalLine() {
    System.out.println("-----");
  }
}
