package detectors

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class IntegerDetectorTest {

    @ParameterizedTest
    @ValueSource(strings = ["1", "123", "3452342352434534524346", "5"])
    fun `valid integers are accepted`(input: String) {
        assertTrue(IntegerDetector.matches(input))
    }

    @ParameterizedTest
    @ValueSource(strings = ["0123", "132a", "0", "a"])
    fun `invalid integers are rejected`(input: String) {
        assertFalse(IntegerDetector.matches(input))
    }

    @org.junit.jupiter.api.Test
    fun `empty string is rejected`() {
        assertFalse(IntegerDetector.matches(""))
    }
}
