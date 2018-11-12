package com.example.game.service;

import com.example.game.domain.ticTacToe.Player;
import com.example.game.domain.ticTacToe.Board;
import com.example.game.domain.ticTacToe.Move;

public interface OutputService {
  void print2dBoard(Board board);
  void printPlayerChoice();
  void printPlayerTurn(Player player);
  void printInvalidMove(Move move);
  void printWinner(Player player);
  void printDrawResult();
  void printWelcomeMessage();
  void printGoodbyeMessage();
}
