package statemachine

object DeadState : State {
    override val isAccepting: Boolean = false
    override fun next(char: Char): State = this
}
