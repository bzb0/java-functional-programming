package com.bzb.javafun.vavr

import com.bzb.javafun.immutables.NodeValue
import com.bzb.javafun.immutables.TagValue
import spock.lang.Specification

import java.time.LocalDate

import static io.vavr.API.List
import static io.vavr.API.None

class NodeServiceTest extends Specification {

  def sut = new NodeService()

  def tags = List(TagValue.Builder().id("1").name("Tag1").build()).toJavaList()
  def nonRootNode = NodeValue.Builder().name("non-root").creationDate(LocalDate.now()).tags(tags).build()
  def taggedRootNode = NodeValue.Builder().name("root").creationDate(LocalDate.now()).tags(tags).build()
  def untaggedRootNode = NodeValue.Builder().name("root").creationDate(LocalDate.now()).tags(Collections.emptySet()).build()

  def "input node is a tagged root with Option" () {
    when:
    def result = sut.isTaggedRootWithOption(taggedRootNode)

    then:
    result.get() == 1
  }

  def "input node is not a root node with Option" () {
    when:
    def result = sut.isTaggedRootWithOption(nonRootNode)

    then:
    result == None()
  }

  def "input node is a tagged root with Either" () {
    when:
    def result = sut.isTaggedRootWithEither(taggedRootNode)

    then:
    result.right().get() == 1
  }

  def "input node is not a root node with Either" () {
    when:
    def result = sut.isTaggedRootWithEither(nonRootNode)

    then:
    result.isLeft()
    result.left().get() == NodeService.NodeErrorCode.NON_ROOT_NODE
  }

  def "input node is an untagged root node with Either" () {
    when:
    def result = sut.isTaggedRootWithEither(untaggedRootNode)

    then:
    result.isLeft()
    result.left().get() == NodeService.NodeErrorCode.UNTAGGED_NODE
  }

  def "input node is a tagged root with Validation" () {
    when:
    def result = sut.isTaggedRootWithValidation(taggedRootNode)

    then:
    result.isValid()
    result.get() == 1
  }

  def "input node is not a root node with Validation" () {
    when:
    def result = sut.isTaggedRootWithValidation(nonRootNode)

    then:
    result.isInvalid()
    result.getError() == NodeService.NodeErrorCode.NON_ROOT_NODE
  }

  def "input node is an untagged root node with Validation" () {
    when:
    def result = sut.isTaggedRootWithValidation(untaggedRootNode)

    then:
    result.isInvalid()
    result.getError() == NodeService.NodeErrorCode.UNTAGGED_NODE
  }

  def "input node is a tagged root with Try" () {
    when:
    def result = sut.isTaggedRootWithTry(taggedRootNode)

    then:
    result.isSuccess()
    result.get() == 1
  }

  def "input node is not a root node with Try" () {
    when:
    def result = sut.isTaggedRootWithTry(nonRootNode)

    then:
    result.isFailure()
    result.getCause().getMessage() == "InvalidNodeException: NON_ROOT_NODE"
  }
}
