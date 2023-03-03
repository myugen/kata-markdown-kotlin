package dev.kata.stringcalculator

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock

internal class StringCalculatorShould {
    @Test
    fun `work`() {
        val stringCalculator = StringCalculator()

        val result = stringCalculator.execute()

        assertThat(result).isEqualTo(0)
    }
}
