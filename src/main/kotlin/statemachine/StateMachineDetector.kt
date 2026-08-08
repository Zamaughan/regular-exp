package statemachine

/**
 * Runs a string through a state machine and reports whether it ended on an
 * accepting state.
 *
 * Every detector in this project (integer, float, binary, email, password)
 * follows the exact same algorithm: start at a start state, feed it one
 * character at a time, see where you land. The only thing that differs
 * between detectors is which states exist and how they are wired, so that
 * part is the only thing each subclass has to provide. This is a small
 * Template Method: the walking-the-string logic lives here once, the
 * per-pattern behavior lives in the State graph passed in.
 */
open class StateMachineDetector(private val startState: State) {

    open fun matches(input: String): Boolean {
        var current = startState
        for (char in input) {
            current = current.next(char)
        }
        return current.isAccepting
    }
}
