package statemachine

interface State {

    /** True if stopping right here means the input so far is a valid match. */
    val isAccepting: Boolean

    /** Given the next character of input, what state do we move to? */
    fun next(char: Char): State
}
