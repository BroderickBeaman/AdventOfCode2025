package aoc2015.dec03;

public record Location(int row, int col) {
    
    public Location move(Character charIn) {
        return switch (charIn) {
            case '>' -> new Location(this.row(), this.col() + 1);
            case '<' -> new Location(this.row(), this.col() - 1);
            case '^' -> new Location(this.row() - 1, this.col());
            case 'v' -> new Location(this.row() + 1, this.col());
            default -> throw new IllegalArgumentException("Not a valid direction");
        };
    }
}
