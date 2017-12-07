package com.bzb.javafun.vavr;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Failure;
import static io.vavr.API.Left;
import static io.vavr.API.Match;
import static io.vavr.API.Right;
import static io.vavr.API.Success;

import com.bzb.javafun.immutables.Node;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;

/**
 * Service class that uses the Vavr control structures {@link Option}, {@link Either}, {@link Validation} and {@link Try}.
 */
public class NodeService {

  public enum NodeErrorCode {NON_ROOT_NODE, UNTAGGED_NODE}

  public Option<Integer> isTaggedRootWithOption(Node node) {
    return isTaggedRoot(node).toOption();
  }

  public Either<NodeErrorCode, Integer> isTaggedRootWithEither(Node node) {
    return isTaggedRoot(node);
  }

  public Validation<NodeErrorCode, Integer> isTaggedRootWithValidation(Node node) {
    return Validation.fromEither(isTaggedRoot(node));
  }

  public Try<Integer> isTaggedRootWithTry(Node node) {
    Either<NodeErrorCode, Integer> taggedRoot = isTaggedRoot(node);
    return taggedRoot.isRight() ? Success(taggedRoot.get()) : Failure(new InvalidNodeException(taggedRoot.getLeft()));
  }

  private Either<NodeErrorCode, Integer> isTaggedRoot(Node node) {
    return Match(node).of(
        Case($(n -> !n.getName().equals("root")), Left(NodeErrorCode.NON_ROOT_NODE)),
        Case($(n -> n.getTags().isEmpty()), Left(NodeErrorCode.UNTAGGED_NODE)),
        Case($(), p -> Right(p.getTags().size()))
    );
  }
}
