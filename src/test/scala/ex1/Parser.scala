package ex1

import ex1.*
import org.junit.Assert.*
import org.junit.Test
import ex1.Parsers.charParser
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class ParserTests extends AnyFunSuite:
  def parser = new BasicParser(Set('a', 'b', 'c'))
  // Note NonEmpty being "stacked" on to a concrete class
  // Bottom-up decorations: NonEmptyParser -> NonEmpty -> BasicParser -> Parser
  def parserNE = new NonEmptyParser(Set('0', '1'))
  def parserNTC = new NotTwoConsecutiveParser(Set('X', 'Y', 'Z'))
  // note we do not need a class name here, we use the structural type
  def parserNTCNE = new BasicParser(Set('X', 'Y', 'Z'))
    with NotTwoConsecutive[Char]
    with NonEmpty[Char]
  def sparser: Parser[Char] = "abc".charParser()

//  @Test
//  def testBasicParser =
//    assertTrue(parser.parseAll("aabc".toList))
//    assertFalse(parser.parseAll("aabcdc".toList))
//    assertTrue(parser.parseAll("aabcdc".toList))

  test("basic parser functionalities")
  parser.parseAll("aabc".toList) should be(true)
  parser.parseAll("aabcdc".toList) should be(false)
  parser.parseAll("".toList) should be(true)

//  @Test
//  def testNotEmptyParser =
//    assertTrue(parserNE.parseAll("0101".toList))
//    assertFalse(parserNE.parseAll("0123".toList))
//    assertFalse(parserNE.parseAll(List()))

  test("notEmptyParser functionalities")
  parserNE.parseAll("0101".toList) should be(true)
  parserNE.parseAll("0123".toList) should be(false)
  parserNE.parseAll(List()) should be(false)

//  @Test
//  def testNotTwoConsecutiveParser =
//    assertTrue(parserNTC.parseAll("XYZ".toList))
//    assertFalse(parserNTC.parseAll("XYYZ".toList))
//    assertTrue(parserNTC.parseAll("".toList))

  test("notTwoConsecutivesParser functionalities")
    parserNTC.parseAll("XYZ".toList) should be (true)
    parserNTC.parseAll("XYYZ".toList) should be (false)
    parserNTC.parseAll("".toList) should be (true)

//  @Test
//  def testNotEmptyAndNotTwoConsecutiveParser =
//    assertTrue(parserNTCNE.parseAll("XYZ".toList))
//    assertFalse(parserNTCNE.parseAll("XYYZ".toList))
//    assertFalse(parserNTCNE.parseAll("".toList))

  test("notEmpty and NotTwoConsecutive parser functionalities")
    parserNTCNE.parseAll("XYZ".toList) should be (true)
    parserNTCNE.parseAll("XYYZ".toList) should be (false)
    parserNTCNE.parseAll("".toList) should be (false)

//  @Test
//  def testStringParser =
//    assertTrue(sparser.parseAll("aabc".toList))
//    assertFalse(sparser.parseAll("aabcdc".toList))
//    assertTrue(sparser.parseAll("".toList))

  test("string parser functionalities")
    sparser.parseAll("aabc".toList) should be (true)
    sparser.parseAll("aabcdc".toList) should be (false)
    sparser.parseAll("".toList) should be (true)

