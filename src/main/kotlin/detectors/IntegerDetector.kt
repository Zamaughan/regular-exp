package detectors

import statemachine.DeadState
import statemachine.State
import statemachine.StateMachineDetector

/**
 * Detects a valid integer: one leading digit 1-9, followed by zero or more
 * digits 0-9, and nothing else. "0", "0123", and "" are all invalid.
 *
 * States:
 *   Start           -- nothing consumed yet
 *   AcceptingDigits -- at least one valid digit consumed so far
 *   DeadState       -- shared trap state
 */
object IntegerDetector : StateMachineDetector(Start) {

    private object Start : State {
        override val isAccepting = false

        override fun next(char: Char): State =
            if (char in '1'..'9') AcceptingDigits else DeadState
    }

    private object AcceptingDigits : State {
        override val isAccepting = true

        override fun next(char: Char): State =
            if (char in '0'..'9') AcceptingDigits else DeadState
    }
}
