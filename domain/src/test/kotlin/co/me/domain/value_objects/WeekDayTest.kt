package co.me.domain.value_objects

import junit.framework.Assert.assertEquals
import org.junit.Test

class WeekDayTest {
    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creating a negative day`() {
        WeekDay(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creating a day greater than 6`() {
        WeekDay(7)
    }

    @Test
    fun `should create a WeekDay with received value`() {
        val weekDay = WeekDay(5)
        assertEquals(weekDay.value, 5)
    }

    @Test
    fun `should create a WeekDay with 0 value`() {
        val weekDay = WeekDay(0)
        assertEquals(weekDay.value, 0)
    }

    @Test
    fun `should create a WeekDay with 6 value`() {
        val weekDay = WeekDay(6)
        assertEquals(weekDay.value, 6)
    }
}