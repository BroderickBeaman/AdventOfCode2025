package aoc2022.dec02;

public enum RPSElement implements Comparable<RPSElement> {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;
    private RPSElement win;
    private RPSElement lose;

    RPSElement(int score) {
        this.score = score;
    }

    public void setWin(RPSElement win) {
        this.win = win;
    }

    public void setLose(RPSElement lose) {
        this.lose = lose;
    }

    public int fightScore(RPSElement other) {
        return other.equals(this.lose) ? 0 : other.equals(win) ? 6 : 3;
    }

    public int totalScore(RPSElement other) {
        return this.fightScore(other) + this.score;
    }

    public static RPSElement fromString(String input) {
        return switch (input) {
            case "A" -> ROCK;
            case "B" -> PAPER;
            case "C" -> SCISSORS;
            default -> throw new IllegalArgumentException("Unsupported string: " + input);
        };
    }

    public RPSElement fromStrategyString(String strat) {
        return switch (strat) {
            case "X" -> this.win;
            case "Y" -> this;
            case "Z" -> this.lose;
            default -> throw new IllegalArgumentException("Unsupported strat: " + strat);
        };
    }

    static {
        ROCK.setWin(SCISSORS);
        ROCK.setLose(PAPER);
        PAPER.setWin(ROCK);
        PAPER.setLose(SCISSORS);
        SCISSORS.setWin(PAPER);
        SCISSORS.setLose(ROCK);
    }
}
