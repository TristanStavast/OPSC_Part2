package com.opsc.opsc_part2

import io.kotest.core.spec.style.StringSpec
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun username_isCorrect()
    {
        assertEquals("tristan", "tristan")
    }

    @Test
    fun password_isCorrect()
    {
        assertEquals("123", "123")
    }
}