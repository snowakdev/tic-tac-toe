package com.example.game.domain.ticTacToe;

import com.example.game.domain.isValid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
@RequiredArgsConstructor
public class Move implements isValid {

  private final int row;
  private final int column;
}
