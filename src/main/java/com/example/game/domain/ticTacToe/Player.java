package com.example.game.domain.ticTacToe;

import com.example.game.domain.isValid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Player implements isValid {
  private final PlayerKind player;
  private Move move;

  public void makeMove(int row, int column) {
    move = new Move(row - 1, column - 1);
  }
}
