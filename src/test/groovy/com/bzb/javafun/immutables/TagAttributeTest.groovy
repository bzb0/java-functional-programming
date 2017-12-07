package com.bzb.javafun.immutables

import spock.lang.Specification

class TagAttributeTest extends Specification {

  def "redacted attributes are masked in toString method"() {
    given:
    def tag = TagValue.Builder().name("Tag1").id("1234").build()

    when:
    def result = tag.toString()

    then:
    result == "Tag{name=Tag1, id=***}"
  }

  def "auxiliary attributes are not included in hashCode and equals" () {
    given:
    def tag1 = TagValue.Builder().id("1").name("Tag").priority(1).build()
    def tag2 = TagValue.Builder().id("1").name("Tag").priority(100).build()

    when:
    def equalsResult = tag1.equals(tag2)

    then:
    equalsResult
    tag1.hashCode() == tag2.hashCode()
  }

  def "default attributes can be set with a custom value" () {
    when:
    def tagWithDefaultPriority = TagValue.Builder().id("1").name("Tag").build()
    def tagWithPriority = TagValue.Builder().id("1").name("Tag").priority(100).build()

    then:
    tagWithDefaultPriority.getPriority() == 1
    tagWithPriority.getPriority() == 100
  }
}
