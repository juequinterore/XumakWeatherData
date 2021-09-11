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

    @Test
    fun `should return 0AM if hour is 0`() {
        //Arrange
        val dayHour = DayHour(0)

        //Act
        val hourString = dayHour.to12HoursString()

        //Assert
        assertEquals("0AM", hourString)
    }

    @Test
    fun `should return 12PM if hour is 12`() {
        //Arrange
        val dayHour = DayHour(12)

        //Act
        val hourString = dayHour.to12HoursString()

        //Assert
        assertEquals("12PM", hourString)
    }

    @Test
    fun `should return 3PM if hour is 15`() {
        //Arrange
        val dayHour = DayHour(15)

        //Act
        val hourString = dayHour.to12HoursString()

        //Assert
        assertEquals("3PM", hourString)
    }

    @Test
    fun `should return 11PM if hour is 23`() {
        //Arrange
        val dayHour = DayHour(23)

        //Act
        val hourString = dayHour.to12HoursString()

        //Assert
        assertEquals("11PM", hourString)
    }
}