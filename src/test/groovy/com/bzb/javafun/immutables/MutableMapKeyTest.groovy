package com.bzb.javafun.immutables


import spock.lang.Specification

class MutableMapKeyTest extends Specification {

  def "mutable object used as a key in a hashmap leads to unexpected lookups"() {
    given: "date object with time 1000, hashmap with the date object as key and date1000 as value"
    def keyDate1000 = new Date(1000)
    def dateMap = new HashMap<Date, String>()
    dateMap.put(keyDate1000, "date1000")

    when: "lookup with date with time 1000"
    def result = dateMap.get(keyDate1000)

    then: "the returned value should be date1000"
    result == "date1000"

    when: "lookup with same date object with time 2000"
    keyDate1000.setTime(2000)
    result = dateMap.get(keyDate1000)

    then: "the returned value should be null"
    result == null

    when: "lookup with a new date object with time 2000"
    result = dateMap.get(new Date(2000))

    then: "the returned value should be null"
    result == null

    when: "lookup with a new date object with time 1000"
    result = dateMap.get(new Date(1000))

    then: "the returned value is null although it should be date1000"
    result == null
  }
}
