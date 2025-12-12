package aoc2022.dec02;

public class Fight {

    private final RPSElement me;
    private final RPSElement other;

    public Fight(RPSElement me, RPSElement other) {
        this.me = me;
        this.other = other;
    }

    public RPSElement getMe() {
        return me;
    }

    public RPSElement getOther() {
        return other;
    }

    public int getScore() {
        return me.totalScore(other);
    }
}
