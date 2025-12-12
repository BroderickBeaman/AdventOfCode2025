package aoc2025.dec07;

public enum Manifold {
    EMPTY('.'),
    SPLITTER('^'),
    BEAM('|'),
    START('S');

    private Character value;

    Manifold(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }

    public static Manifold fromChar(Character charIn) {
        for (Manifold manifold : values()) {
            if (manifold.value.equals(charIn)) {
                return manifold;
            }
        }
        throw new IllegalArgumentException("Unsupported Manifold: " + charIn);
    }
}
