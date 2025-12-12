package aoc2025.dec06;

import java.util.Optional;
import java.util.function.BinaryOperator;

public enum Operator {
    ADD,
    MULTIPLY;

    public static Optional<Operator> fromChar(char charIn) {
        return switch (charIn) {
            case '+' -> Optional.of(ADD);
            case '*' -> Optional.of(MULTIPLY);
            default -> Optional.empty();
        };
    }
    
    public BinaryOperator<Long> operation() {
        return switch (this) {
            case ADD -> Long::sum;
            case MULTIPLY -> (left, right) -> left * right;
        };
    }
}
