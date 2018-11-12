package com.example.game;

import com.example.game.repository.ticTacToe.SystemInputService;
import com.example.game.repository.ticTacToe.TicTacToeService;
import com.example.game.repository.ticTacToe.ConsolePrintService;
import com.example.game.service.GameService;
import com.example.game.service.InputService;
import com.example.game.service.OutputService;

public class App {

  public static void main(String[] args) {
    InputService inputService = new SystemInputService();
    OutputService outputService = new ConsolePrintService();
    GameService gameService = new TicTacToeService(inputService, outputService);
    gameService.play();
  }
}
