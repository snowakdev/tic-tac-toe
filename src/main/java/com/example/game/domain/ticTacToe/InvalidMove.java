package com.example.game.domain.ticTacToe;

import com.example.game.domain.isNotValid;
import lombok.ToString;

@ToString
public final class InvalidMove extends Move implements isNotValid {
  private static final InvalidMove INSTANCE = new InvalidMove();

  private InvalidMove() {
    super(0, 0);
  }

  public static InvalidMove create() {
    return INSTANCE;
  }

  @Override
  public boolean isValid() {
    return false;
  }
}
