package detectors

import statemachine.State
import statemachine.StateMachineDetector

/**
 * Detects a complex password: at least one capital letter, at least one
 * special character (!@#$%&*), does not end with a special character, and
 * is at least 8 characters long.
 *
 * The length check is deliberately not part of the state machine, counting
 * characters is not a job for a state (that road leads to one state per
 * length, which gets out of hand fast) so it is just a plain length check
 * layered on top before the machine even runs.
 *
 * The state itself only needs to remember three yes/no facts: has a
 * capital been seen, has a special character been seen, and was the most
 * recently consumed character special. That is a single reusable State
 * shape (a small Flyweight, states differing only by three booleans do
 * not need eight hand written classes, one data class covers all of
 * them) rather than a named object per state like the other detectors.
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
