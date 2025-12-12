package aoc2025.dec12;

public record PresentSlot(Long rows, Long cols, Long numPresents) {
    public boolean fits() {
        return numPresents <= (rows / 3) * (cols / 3);
    }
}
