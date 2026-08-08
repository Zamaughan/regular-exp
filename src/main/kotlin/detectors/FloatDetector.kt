package detectors

import statemachine.DeadState
import statemachine.State
import statemachine.StateMachineDetector

/**
 * States:
 *   Start               -- nothing consumed yet
 *   LeadingZero          -- saw a single leading 0, next char must be '.'
 *   WholeNumberDigits    -- consuming digits before any period
 *   PeriodSeenNoDigit    -- just saw the period, still need a digit
 *   PeriodSeenWithDigit  -- accepting, period plus at least one digit seen
 *   DeadState            -- shared trap state
 */
object FloatDetector : StateMachineDetector(Start) {

    private object Start : State {
        override val isAccepting = false

        override fun next(char: Char): State = when {
            char == '.' -> PeriodSeenNoDigit
            char == '0' -> LeadingZero
            char in '1'..'9' -> WholeNumberDigits
            else -> DeadState
        }
    }

    private object LeadingZero : State {
        override val isAccepting = false

        override fun next(char: Char): State =
            if (char == '.') PeriodSeenNoDigit else DeadState
    }

    private object WholeNumberDigits : State {
        override val isAccepting = false

        override fun next(char: Char): State = when {
            char in '0'..'9' -> WholeNumberDigits
            char == '.' -> PeriodSeenNoDigit
            else -> DeadState
        }
    }

    private object PeriodSeenNoDigit : State {
        override val isAccepting = false

        override fun next(char: Char): State =
            if (char in '0'..'9') PeriodSeenWithDigit else DeadState
    }

    private object PeriodSeenWithDigit : State {
        override val isAccepting = true

        override fun next(char: Char): State =
            if (char in '0'..'9') PeriodSeenWithDigit else DeadState
    }
}
