package detectors

import statemachine.DeadState
import statemachine.State
import statemachine.StateMachineDetector

/**
 * States:
 *   Start       -- nothing consumed yet
 *   EndsInOne   -- accepting, most recent digit was a 1
 *   EndsInZero  -- not accepting, most recent digit was a 0
 *   DeadState   -- shared trap state
 */
object BinaryDetector : StateMachineDetector(Start) {

    private object Start : State {
        override val isAccepting = false

        override fun next(char: Char): State =
            if (char == '1') EndsInOne else DeadState
    }

    private object EndsInOne : State {
        override val isAccepting = true

        override fun next(char: Char): State = when (char) {
            '1' -> EndsInOne
            '0' -> EndsInZero
            else -> DeadState
        }
    }

    private object EndsInZero : State {
        override val isAccepting = false

        override fun next(char: Char): State = when (char) {
            '1' -> EndsInOne
            '0' -> EndsInZero
            else -> DeadState
        }
    }
}
