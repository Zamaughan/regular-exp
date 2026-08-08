package detectors

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EmailDetectorTest {

    @ParameterizedTest
    @ValueSource(strings = ["a@b.c", "joseph.ditton@usu.edu", "{}*\$.&\$*(@*\$%&.*&*"])
    fun `valid emails are accepted`(input: String) {
        assertTrue(EmailDetector.matches(input))
    }

    @ParameterizedTest
    @ValueSource(strings = ["@b.c", "a@b@c.com", "a.b@b.b.c", "joseph ditton@usu.edu", "a@b.", "abc.com", "a@.c"])
    fun `invalid emails are rejected`(input: String) {
        assertFalse(EmailDetector.matches(input))
    }

    @Test
    fun `empty string is rejected`() {
        assertFalse(EmailDetector.matches(""))
    }
}
