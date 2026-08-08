package statemachine

/**
 * The trap state. Once a string breaks the rules of a pattern there is no
 * character that can ever fix it, so every detector routes bad input here
 * and it just loops on itself forever. It never accepts.
 *
 * This is shared by every detector (Singleton), there is no reason for
 * IntegerDetector's dead end and EmailDetector's dead end to be different
 * objects, "invalid, and staying invalid" is identical everywhere.
 */
object DeadState : State {
    override val isAccepting: Boolean = false
    override fun next(char: Char): State = this
}
