package statemachine


open class StateMachineDetector(private val startState: State) {

    open fun matches(input: String): Boolean {
        var current = startState
        for (char in input) {
            current = current.next(char)
        }
        return current.isAccepting
    }
}
