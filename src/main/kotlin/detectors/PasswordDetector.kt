package detectors

import statemachine.State
import statemachine.StateMachineDetector

/**
 * Detects a complex password: at least one capital letter, at least one
 * special character (!@#$%&*), does not end with a special character, and
 * is at least 8 characters long.
 */
object PasswordDetector : StateMachineDetector(PasswordState(false, false, false)) {

    private const val MIN_LENGTH = 8

    override fun matches(input: String): Boolean =
        input.length >= MIN_LENGTH && super.matches(input)
}

private val SPECIAL_CHARS = charArrayOf('!', '@', '#', '$', '%', '&', '*')

private data class PasswordState(
    val hasCapital: Boolean,
    val hasSpecial: Boolean,
    val lastWasSpecial: Boolean
) : State {

    override val isAccepting: Boolean
        get() = hasCapital && hasSpecial && !lastWasSpecial

    override fun next(char: Char): State {
        val isSpecial = char in SPECIAL_CHARS
        return PasswordState(
            hasCapital = hasCapital || char in 'A'..'Z',
            hasSpecial = hasSpecial || isSpecial,
            lastWasSpecial = isSpecial
        )
    }
}
