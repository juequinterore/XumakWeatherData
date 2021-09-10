package co.me.domain.value_objects

import org.junit.Assert.assertEquals
import org.junit.Test

class DayHourTest {

    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creation with negative values`() {
        DayHour(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creation with values higher than 23`() {
        DayHour(24)
    }

    @Test
    fun `should create DayHour with received hour`() {
        val dayHour = DayHour(21)
        assertEquals(dayHour.value, 21)
    }

    @Test
    fun `should create DayHour with 0 hour`() {
        val dayHour = DayHour(0)
        assertEquals(dayHour.value, 0)
    }

    @Test
    fun `should create DayHour with 23 hour`() {
        val dayHour = DayHour(23)
        assertEquals(dayHour.value, 23)
    }

}