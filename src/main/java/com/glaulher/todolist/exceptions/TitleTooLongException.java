package com.glaulher.todolist.exceptions;

public class TitleTooLongException extends RuntimeException {
  public TitleTooLongException(String message) {
    super(message);
  }
}
