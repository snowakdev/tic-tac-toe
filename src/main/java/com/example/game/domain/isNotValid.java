package com.example.game.domain;

public interface isNotValid {
  default boolean isValid() {
    return false;
  }
}

