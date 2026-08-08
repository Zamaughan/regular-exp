package detectors

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PasswordDetectorTest {

    @ParameterizedTest
    @ValueSource(strings = [
        "aaaaH!aa",
        "1234567*9J",
        "asdpoihj;loikjasdf;ijp;lij2309jasd;lfkm20ij@aH",
        "aaH!aaaa"
    ])
    fun `valid passwords are accepted`(input: String) {
        assertTrue(PasswordDetector.matches(input))
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "a",
        "aaaaaaa!",
        "aaaHaaaaa",
        "Abbbbbbb!",
        "aaaaaaH*",
        "aaaH!aa"
    ])
    fun `invalid passwords are rejected`(input: String) {
        assertFalse(PasswordDetector.matches(input))
    }

    @Test
    fun `empty string is rejected`() {
        assertFalse(PasswordDetector.matches(""))
    }
}
