package com.example.game.service;

import com.example.game.domain.ticTacToe.Board;
import com.example.game.domain.ticTacToe.Player;

public interface GameService {
  Board initGame();
  Player chooseAPlayer();
  void playerMove(Board board, Player player);
  void updateGameStatus(Board board);
  boolean isDraw(Board board);
  boolean hasWon(Board board);
  void play();
}
