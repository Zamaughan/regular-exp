package detectors

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class FloatDetectorTest {

    @ParameterizedTest
    @ValueSource(strings = ["1.0", "123.34", "0.20000", "12349871234.12340981234098", ".123", "0.0"])
    fun `valid floats are accepted`(input: String) {
        assertTrue(FloatDetector.matches(input))
    }

    @ParameterizedTest
    @ValueSource(strings = ["123", "123.123.", "123.02a", "123.", "012.4", "00.5", "."])
    fun `invalid floats are rejected`(input: String) {
        assertFalse(FloatDetector.matches(input))
    }

    @Test
    fun `empty string is rejected`() {
        assertFalse(FloatDetector.matches(""))
    }
}
