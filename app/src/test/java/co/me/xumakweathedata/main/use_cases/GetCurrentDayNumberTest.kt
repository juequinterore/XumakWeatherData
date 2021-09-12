package co.me.xumakweathedata.main.use_cases

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCurrentDayNumberTest {

    @Test
    fun `should properly return the converted day starting on monday`() = runBlocking {

        val getCurrentDayNumber = GetCurrentDayNumber()

        val map = mapOf(1 to 6, 2 to 0, 3 to 1, 4 to 2, 5 to 3, 6 to 4, 7 to 5)
        map.entries.forEach {
            val modifiedDay = getCurrentDayNumber(GetCurrentDayCommand(it.key))
            assertEquals(modifiedDay, it.value)
        }
    }

}