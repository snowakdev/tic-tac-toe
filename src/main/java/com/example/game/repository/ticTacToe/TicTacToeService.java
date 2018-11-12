package com.example.game.repository.ticTacToe;

import com.example.game.domain.ticTacToe.InvalidMove;
import com.example.game.domain.ticTacToe.InvalidPlayer;
import com.example.game.domain.ticTacToe.PlayerKind;
import com.example.game.domain.ticTacToe.Board;
import com.example.game.domain.ticTacToe.Move;
import com.example.game.domain.ticTacToe.Player;
import com.example.game.helpers.ticTacToe.GamePrefConsts;
import com.example.game.service.GameService;
import com.example.game.service.InputService;
import com.example.game.service.OutputService;

public class TicTacToeService implements GameService {

  private final InputService inputService;
  private final OutputService outputService;

  public TicTacToeService(InputService inputService, OutputService outputService) {
    this.inputService = inputService;
    this.outputService = outputService;
  }

  @Override
  public Board initGame() {
    outputService.printWelcomeMessage();
    Board board = Board.create();
    outputService.print2dBoard(board);
    return board;
  }

  @Override
  public Player chooseAPlayer() {
    int step = 0;
    Player player = InvalidPlayer.create();
    do {
      outputService.printPlayerChoice();
      PlayerKind playerKind = PlayerKind.create(inputService.input());
      if (playerKind.isValid()){
        player = new Player(playerKind);
        step = 3;
      } else {
        ++step;
      }
    } while ((!player.isValid()) && step < 3);
    return player;
  }

  @Override
  public void playerMove(Board board, Player player) {
    int step = 0;
    Move move = InvalidMove.create();
    board.setCurrentPlayer(player);
    do {
      outputService.printPlayerTurn(player);
      int row = inputService.inputInt();
      int column = inputService.inputInt();
      player.makeMove(row, column);
      move = board.takeTurn(player);
      if(!move.isValid()){
        outputService.printInvalidMove(new Move(board.getCurrentMove().getRow() + 1, board.getCurrentMove().getColumn() + 1));
        ++step;
      } else {
        step = 3;
      }
    } while ((!move.isValid()) && step < 3);
  }

  @Override
  public void updateGameStatus(Board board) {
    if(hasWon(board)){
      Player player = board.getCurrentPlayer();
      int currentState = player.getPlayer() == PlayerKind.X ? GamePrefConsts.CROSS_PLAYER_WON : GamePrefConsts.NOUGHT_PLAYER_WON;
      board.setCurrentState(currentState);
      outputService.printWinner(player);
    } else if(isDraw(board)) {
      board.setCurrentState(GamePrefConsts.DRAW);
      outputService.printDrawResult();
    }
  }

  @Override
  public boolean isDraw(Board board) {
    for (int row = 0; row < GamePrefConsts.BOARD_SIZE; ++row) {
      for (int col = 0; col < GamePrefConsts.BOARD_SIZE; ++col) {
        if (board.getBoard()[row][col] == GamePrefConsts.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean hasWon(Board board) {
    Move move = board.getCurrentMove();
    int currentMark = board.getCurrentPlayer().getPlayer().getPlayerMark();
    int currentRow = move.getRow();
    int currentCol = move.getColumn();
    return (board.getBoard()[currentRow][0] == currentMark
        && board.getBoard()[currentRow][1] == currentMark
        && board.getBoard()[currentRow][2] == currentMark
        || board.getBoard()[0][currentCol] == currentMark
        && board.getBoard()[1][currentCol] == currentMark
        && board.getBoard()[2][currentCol] == currentMark
        || currentRow == currentCol
        && board.getBoard()[0][0] == currentMark
        && board.getBoard()[1][1] == currentMark
        && board.getBoard()[2][2] == currentMark
        || currentRow + currentCol == 2
        && board.getBoard()[0][2] == currentMark
        && board.getBoard()[1][1] == currentMark
        && board.getBoard()[2][0] == currentMark);
  }

  @Override
  public void play() {
    Board board = initGame();
    Player player = chooseAPlayer();
    if (player.isValid()) {
      do {
        playerMove(board, player);
        if(!board.getCurrentMove().isValid()){
          outputService.printGoodbyeMessage();
          board.setCurrentState(GamePrefConsts.INVALID);
        } else {
          outputService.print2dBoard(board);
          updateGameStatus(board);
          if(board.getCurrentState() != GamePrefConsts.PLAYING) {
            break;
          }
          player = board.getCurrentPlayer().getPlayer() == PlayerKind.X ? new Player(PlayerKind.O) : new Player(PlayerKind.X);
          board.setCurrentPlayer(player);
        }
      } while (board.getCurrentState() == GamePrefConsts.PLAYING);
    } else {
      outputService.printGoodbyeMessage();
    }
  }
}
