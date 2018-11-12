package com.example.game.repository.ticTacToe;

import com.example.game.domain.ticTacToe.InvalidPlayer;
import com.example.game.domain.ticTacToe.Move;
import com.example.game.domain.ticTacToe.Player;
import com.example.game.domain.ticTacToe.PlayerKind;
import com.example.game.domain.ticTacToe.Board;
import com.example.game.helpers.ticTacToe.GamePrefConsts;
import com.example.game.service.InputService;
import com.example.game.service.OutputService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeServiceTest{

  private Board board;
  private Player player;

  @InjectMocks
  private TicTacToeService ticTacToeService;

  @Mock
  private InputService inputService;

  @Mock
  private OutputService outputService;


  @Before
  public void setUp() throws Exception {
    ticTacToeService = new TicTacToeService(inputService, outputService);
  }

  @Test
  public void testInitGame() {
    board = ticTacToeService.initGame();
    assertEquals(3, board.getBoard()[0].length);
    assertEquals(3, board.getBoard()[1].length);
    assertEquals(3, board.getBoard()[2].length);
    assertEquals(GamePrefConsts.PLAYING, board.getCurrentState());
  }

  @Test
  public void testInvalidPlayer() {
   when(inputService.input()).thenReturn("*");
   player = ticTacToeService.chooseAPlayer();
   assertEquals(InvalidPlayer.create(), player);
   verify(inputService, times(3)).input();
  }

  @Test
  public void testValidPlayer() {
    when(inputService.input()).thenReturn("x");
    player = ticTacToeService.chooseAPlayer();
    assertTrue(player.isValid());
    assertEquals(player.getPlayer().name(), PlayerKind.X.name());
    verify(inputService, times(1)).input();
  }

  @Test
  public void testCorrectPlayerMove() {
    when(inputService.inputInt()).thenReturn(1, 2);
    givenABoard();
    givenAPlayer();
    ticTacToeService.playerMove(board, player);
    verify(inputService, times(2)).inputInt();
  }

  @Test
  public void testIncorrectPlayerMove() {
    when(inputService.inputInt()).thenReturn(3, 3);
    givenABoard();
    filledBoard(2,2, GamePrefConsts.CROSS);
    givenAPlayer();
    ticTacToeService.playerMove(board, player);
    verify(inputService, times(6)).inputInt();
  }

  @Test
  public void testWinner() {
    givenABoard();
    givenAPlayer();
    filledBoard(0,0, GamePrefConsts.CROSS);
    filledBoard(1,1, GamePrefConsts.NOUGHT);
    filledBoard(0,1, GamePrefConsts.CROSS);
    filledBoard(2,2, GamePrefConsts.NOUGHT);
    filledBoard(0,2, GamePrefConsts.CROSS);
    givenACurrentPlayer(player);
    givenACurrentMove(new Move(0,2));
    assertTrue(ticTacToeService.hasWon(board));
  }

  @Test
  public void testNoWinner() {
    givenABoard();
    givenAPlayer();
    filledBoard(0,0, GamePrefConsts.CROSS);
    filledBoard(1,1, GamePrefConsts.NOUGHT);
    filledBoard(0,1, GamePrefConsts.CROSS);
    givenACurrentPlayer(player);
    givenACurrentMove(new Move(0,1));
    assertFalse(ticTacToeService.hasWon(board));
  }

  @Test
  public void testNoDraw() {
    givenABoard();
    filledBoard(0, 0, GamePrefConsts.CROSS);
    assertFalse(ticTacToeService.isDraw(board));
  }

  @Test
  public void testDraw() {
    givenABoard();
    filledBoard(0,0, GamePrefConsts.CROSS);
    filledBoard(0,1, GamePrefConsts.NOUGHT);

    filledBoard(0,2, GamePrefConsts.CROSS);
    filledBoard(1,0, GamePrefConsts.NOUGHT);

    filledBoard(1,1, GamePrefConsts.CROSS);
    filledBoard(1,2, GamePrefConsts.NOUGHT);

    filledBoard(2,1, GamePrefConsts.CROSS);
    filledBoard(2,0, GamePrefConsts.NOUGHT);
    assertFalse(ticTacToeService.isDraw(board));
  }

  private void givenABoard() {
    board = Board.create();
  }

  private void filledBoard(int row, int column, int value) {
    board.getBoard()[row][column] = value;
}

  private void givenAPlayer() {
    player = new Player(PlayerKind.X);
  }

  private void givenACurrentPlayer(Player player) {
    board.setCurrentPlayer(player);
  }

  private void givenACurrentMove(Move move) {
    board.setCurrentMove(move);
  }
}
