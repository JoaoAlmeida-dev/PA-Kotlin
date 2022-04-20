package Exercise4

import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestClass {
    var dummy = 0

    @BeforeTestRun
    fun setup() {
        dummy = 10
    }

    @TestCase
    fun testDummyNotEqualsDummy() {
        assertFalse(dummy == dummy, "$dummy cannot be equal to $dummy")
        dummy++
    }

    @TestCase
    fun testDummyDifferentDummy() {
        assertTrue(dummy != dummy, "$dummy must be different to $dummy")
        dummy++
    }

    @TestCase
    fun testDummyIs10() {
        assertTrue(dummy == 10, "$dummy must be equal to 10")
        dummy++
    }

    fun other() {
        println("other")
    }
}
