package com.example.game.domain.ticTacToe;

import com.example.game.domain.isNotValid;
import lombok.ToString;

@ToString
public final class InvalidPlayer extends Player implements isNotValid {
  private static final InvalidPlayer INSTANCE = new InvalidPlayer();

  private InvalidPlayer() {
   super(PlayerKind.INVALID);
  }

  public static InvalidPlayer create() {
    return INSTANCE;
  }

  @Override
  public boolean isValid() {
    return false;
  }
}
