package com.example.game.domain.ticTacToe;

import junit.framework.TestCase;
import org.junit.Test;

public class BoardTest extends TestCase {

  @Test
  public void testTakeTurnWithValidMove() {
    Player player = new Player(PlayerKind.X);
    player.makeMove(3,3);
    Board board = Board.create();
    Move move = board.takeTurn(player);
    assertTrue(move.isValid());
  }

  @Test
  public void testTakeTurnWithInvalidMove() {
    Player player = new Player(PlayerKind.X);
    player.makeMove(4,4);
    Board board = Board.create();
    Move move = board.takeTurn(player);
    assertFalse(move.isValid());
  }
}
