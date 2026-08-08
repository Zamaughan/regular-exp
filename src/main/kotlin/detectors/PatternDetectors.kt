package detectors

enum class PatternType {
    INTEGER, FLOAT, BINARY, EMAIL, PASSWORD
}

object DetectorFactory {
    fun get(type: PatternType): (String) -> Boolean = when (type) {
        PatternType.INTEGER -> IntegerDetector::matches
        PatternType.FLOAT -> FloatDetector::matches
        PatternType.BINARY -> BinaryDetector::matches
        PatternType.EMAIL -> EmailDetector::matches
        PatternType.PASSWORD -> PasswordDetector::matches
    }
}
