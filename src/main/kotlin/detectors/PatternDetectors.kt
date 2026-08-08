package detectors

/** The five string shapes this project can detect. */
enum class PatternType {
    INTEGER, FLOAT, BINARY, EMAIL, PASSWORD
}

/**
 * Small Factory so callers can go from "which pattern do I need" to "a
 * function that checks it" without knowing the individual detector object
 * names. Not required by the assignment, just a convenient front door.
 */
object DetectorFactory {
    fun get(type: PatternType): (String) -> Boolean = when (type) {
        PatternType.INTEGER -> IntegerDetector::matches
        PatternType.FLOAT -> FloatDetector::matches
        PatternType.BINARY -> BinaryDetector::matches
        PatternType.EMAIL -> EmailDetector::matches
        PatternType.PASSWORD -> PasswordDetector::matches
    }
}
