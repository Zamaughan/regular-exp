package detectors

import statemachine.DeadState
import statemachine.State
import statemachine.StateMachineDetector

/**
 * Detects addresses shaped like part1@part2.part3 ("a@b.c",
 * "joseph.ditton@usu.edu", "{}*$.&$*(@*$%&.*&*"). No spaces anywhere, no
 * second '@', and none of the three parts can be empty. A period is only
 * restricted after the '@', part1 can contain as many periods as it wants
 * since the "exactly one period" rule only applies once we are past the
 * '@' symbol.
 *
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
