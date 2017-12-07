package com.bzb.javafun.immutables

import spock.lang.Specification

import java.time.LocalDate

class NodeCreationTest extends Specification {

  def "NodeValue.Builder creates an immutable Node"() {
    when:
    def node = NodeValue.Builder().name("Node1").creationDate(LocalDate.now()).creatorName("Tester").build()

    then:
    node.getName() == "Node1"
    node.getCreatorName().get() == "Tester"
    node.getCreationDate() == LocalDate.now()
  }

  def "NodeValue.of creates an immutable Node"() {
    when:
    def node = NodeValue.of("Node", LocalDate.now(), Optional.of("Tester"), Collections.emptySet())

    then:
    node.getName() == "Node"
    node.getCreatorName().get() == "Tester"
    node.getCreationDate() == LocalDate.now()
    node.getTags().isEmpty()
  }

  def "NodeValue.Builder throws NullPointerException for null values"() {
    when:
    NodeValue.Builder().name(null).creatorName("Tester").build()

    then:
    thrown(NullPointerException)
  }

  def "NodeValue.Builder throws IllegalStateException for invalid input values"() {
    when:
    NodeValue.Builder().name("Test").creationDate(LocalDate.now().plusMonths(1)).build()

    then:
    def exception = thrown(IllegalStateException)
    exception.getMessage() == "The creation date must be in the past."
  }
}
