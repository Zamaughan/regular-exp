package statemachine

/**
 * A single state in a hand built state machine.
 *
 * Every detector in this project is a graph of State objects. Each state
 * only needs to know two things: what state comes next given the current
 * character, and whether stopping here would mean the string seen so far
 * is a valid match. Nothing outside of a state ever needs to know how many
 * states exist or how they are wired together, that is the whole point of
 * the State pattern, the "what happens on the next character" logic lives
 * inside the state itself instead of a giant if/else chain somewhere else.
 */
interface State {

    /** True if stopping right here means the input so far is a valid match. */
    val isAccepting: Boolean

    /** Given the next character of input, what state do we move to? */
    fun next(char: Char): State
}
