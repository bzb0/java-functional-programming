package com.bzb.javafun.vavr;

import com.bzb.javafun.vavr.NodeService.NodeErrorCode;

public class InvalidNodeException extends RuntimeException {

  public InvalidNodeException(NodeErrorCode errorCode) {
    super("InvalidNodeException: " + errorCode.name());
  }
}
