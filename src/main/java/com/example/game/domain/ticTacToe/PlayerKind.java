package com.example.game.domain.ticTacToe;

import com.example.game.helpers.ticTacToe.GamePrefConsts;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public enum PlayerKind {
  X("CROSS", GamePrefConsts.CROSS), O("NOUGHT", GamePrefConsts.NOUGHT), INVALID("", GamePrefConsts.EMPTY, false);

  private boolean valid;
  private String playerName;
  private int playerMark;

  PlayerKind(String playerName, int playerMark) {
    this.playerName = playerName;
    this.playerMark = playerMark;
    this.valid = true;
  }

  PlayerKind(String playerName, int playerMark, boolean unknown) {
    this.playerName = playerName;
    this.playerMark = playerMark;
    this.valid = unknown;
  }

  public boolean isValid() {
    return this.valid;
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public int getPlayerMark() {
    return this.playerMark;
  }

  public static PlayerKind create(String playerCode) {
    PlayerKind result;
    try {
      if (StringUtils.isNotEmpty(playerCode)) {
        String transformedPlayerCode = playerCode.toUpperCase(Locale.getDefault());
        result = PlayerKind.valueOf(transformedPlayerCode);
      } else {
        result = PlayerKind.INVALID;
      }

    } catch (IllegalArgumentException e) {
      result = PlayerKind.INVALID;
    }
    return result;
  }


}
