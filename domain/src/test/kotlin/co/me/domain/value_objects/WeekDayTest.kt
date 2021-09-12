package co.me.domain.value_objects

import org.junit.Assert.assertEquals
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

    @Test
    fun `should return proper short name per each WeekDay`() {
        val map = mapOf(
            WeekDay(0) to "Mon",
            WeekDay(1) to "Tue",
            WeekDay(2) to "Wed",
            WeekDay(3) to "Thu",
            WeekDay(4) to "Fri",
            WeekDay(5) to "Sat",
            WeekDay(6) to "Sun",
        )

        map.entries.onEach {
            assertEquals(it.key.toShortName(), it.value)
        }
    }

}