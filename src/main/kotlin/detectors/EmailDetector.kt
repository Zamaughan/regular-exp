package detectors

import statemachine.DeadState
import statemachine.State
import statemachine.StateMachineDetector

/**
 * States:
 *   LocalPartEmpty -- before '@', nothing consumed yet
 *   LocalPart      -- before '@', at least one char consumed
 *   DomainEmpty    -- after '@', before the required period, nothing consumed
 *   Domain         -- after '@', before the required period, at least one char consumed
 *   TldEmpty       -- after the required period, nothing consumed
 *   Tld            -- accepting, after the period, at least one char consumed
 *   DeadState      -- shared trap state
 */
object EmailDetector : StateMachineDetector(LocalPartEmpty) {

    private object LocalPartEmpty : State {
        override val isAccepting = false

        override fun next(char: Char): State = when (char) {
            ' ', '@' -> DeadState
            else -> LocalPart
        }
    }

    private object LocalPart : State {
        override val isAccepting = false

        override fun next(char: Char): State = when (char) {
            ' ' -> DeadState
            '@' -> DomainEmpty
            else -> LocalPart
        }
    }

    private object DomainEmpty : State {
        override val isAccepting = false

        override fun next(char: Char): State = when (char) {
            ' ', '@', '.' -> DeadState
            else -> Domain
        }
    }

    private object Domain : State {
        override val isAccepting = false

        override fun next(char: Char): State = when (char) {
            ' ', '@' -> DeadState
            '.' -> TldEmpty
            else -> Domain
        }
    }

    private object TldEmpty : State {
        override val isAccepting = false

        override fun next(char: Char): State = when (char) {
            ' ', '@', '.' -> DeadState
            else -> Tld
        }
    }

    private object Tld : State {
        override val isAccepting = true

        override fun next(char: Char): State = when (char) {
            ' ', '@', '.' -> DeadState
            else -> Tld
        }
    }
}
