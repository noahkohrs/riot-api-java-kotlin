package com.noahkohrs.riot.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("unused")
class ResponseTest {
    internal class TestClass1(
        val a: Int,
        val b: String,
    ) : Response()

    /**
     * These tests only covers the basic behaviour of the Response class.
     */
    @Nested
    inner class SimpleTests {
        @Test
        fun testCopy() {
            val testObj1 = TestClass1(1, "test")
            val testObj2 = testObj1.copy()
            val testObj3 = testObj1.copyAndCastTo<TestClass1>()
            assertEquals(testObj1, testObj2)
            assertEquals(testObj1, testObj3)
        }

        @Test
        fun testHashCode() {
            val testClass1 = TestClass1(1, "test")
            println(testClass1.hashCode())
            val testClass2 = TestClass1(1, "test")
            println(testClass2.hashCode())
            assertEquals(testClass1.hashCode(), testClass2.hashCode())
        }

        @Test
        fun testEquals() {
            val testClass1 = TestClass1(1, "test")
            val testClass2 = TestClass1(1, "test")
            assertEquals(testClass1, testClass2)

            class TestClass2(
                val a: Int,
                val b: String,
            ) : Response()
            val testClass3 = TestClass2(1, "test")
            assertNotEquals(testClass1, testClass3)
        }

        @Test
        fun testToString() {
            val testClass1 = TestClass1(1, "test")
            assertEquals("TestClass1(a=1, b=test)", testClass1.toString())
        }
    }

    /**
     * Here should appear specific behaviours while encountering something complex.
     */
    @Nested
    inner class PreciseBehaviours {
        @Test
        fun `values from outside of the constructor`() {
            class TestClass2(
                val a: Int,
                val b: String,
            ) : Response() {
                val c: Int
                    get() = a + 1
            }
            val testClass1 = TestClass2(1, "test")
            assertEquals("TestClass2(a=1, b=test, c=2)", testClass1.toString())
        }
    }
}
