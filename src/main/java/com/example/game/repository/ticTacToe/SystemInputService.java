package com.example.game.repository.ticTacToe;

import com.example.game.service.InputService;
import lombok.Value;

import java.util.Scanner;

@Value
public class SystemInputService implements InputService {

  private final Scanner scanner = new Scanner(System.in);

  @Override
  public int inputInt() {
    return scanner.nextInt();
  }

  public String input() {
    return scanner.next();
  }
}
