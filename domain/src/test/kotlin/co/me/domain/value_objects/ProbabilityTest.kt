package co.me.domain.value_objects

import org.junit.Assert.assertEquals
import org.junit.Test

class ProbabilityTest {

    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creating negative probability`() {
        Probability(-0.1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should not allow probabilities greater than one`() {
        Probability(1.1)
    }

    @Test
    fun `should create a probability with received number`() {
        val probability = Probability(0.4)
        assertEquals(probability.value, 0.4, 0.001)
    }

    @Test
    fun `should create a probability of 0`() {
        val probability = Probability(0.0)
        assertEquals(probability.value, 0.0, 0.001)
    }

    @Test
    fun `should create a probability of 1`() {
        val probability = Probability(1.0)
        assertEquals(probability.value, 1.0, 0.001)
    }

    @Test
    fun `should return 0%`() {
        //Arrange
        val probability = Probability(0.0)

        //Act
        val percentageString = probability.toPercentageString()

        //Assert
        assertEquals(percentageString, "0%")
    }

    @Test
    fun `should return 0,05%`() {
        //Arrange
        val probability = Probability(0.0005)

        //Act
        val percentageString = probability.toPercentageString()

        //Assert
        assertEquals(percentageString, "0.05%")
    }

    @Test
    fun `should return 50%`() {
        //Arrange
        val probability = Probability(0.5)

        //Act
        val percentageString = probability.toPercentageString()

        //Assert
        assertEquals(percentageString, "50%")
    }

    @Test
    fun `should return 50,5%`() {
        //Arrange
        val probability = Probability(0.505)

        //Act
        val percentageString = probability.toPercentageString()

        //Assert
        assertEquals(percentageString, "50.5%")
    }

    @Test
    fun `should return 100%`() {
        //Arrange
        val probability = Probability(1.0)

        //Act
        val percentageString = probability.toPercentageString()

        //Assert
        assertEquals(percentageString, "100%")
    }

}