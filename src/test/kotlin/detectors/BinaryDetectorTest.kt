package detectors

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BinaryDetectorTest {

    @ParameterizedTest
    @ValueSource(strings = ["1", "11", "101", "111111", "10011010001"])
    fun `valid binary numbers are accepted`(input: String) {
        assertTrue(BinaryDetector.matches(input))
    }

    @ParameterizedTest
    @ValueSource(strings = ["01", "10", "1000010", "100a01", "0"])
    fun `invalid binary numbers are rejected`(input: String) {
        assertFalse(BinaryDetector.matches(input))
    }

    @Test
    fun `empty string is rejected`() {
        assertFalse(BinaryDetector.matches(""))
    }
}
