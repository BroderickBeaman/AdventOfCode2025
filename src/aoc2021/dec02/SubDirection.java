package aoc2021.dec02;

public enum SubDirection {
    FORWARD("forward"),
    DOWN("down"),
    UP("up");

    private String value;

    SubDirection(String value) {
        this.value = value;
    }

    public static SubDirection fromString(String in) {
        for (SubDirection direction : values()) {
            if (direction.value.equals(in)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Unsupported SubDirection: " + in);
    }
}
