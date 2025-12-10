package dec09;

import framework.AOCParent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dec09 extends AOCParent {

    private List<Point> points;
    private List<Line> lines;

    @Override
    public void loadInput() {
        points = InputLoader.loadPoints();
    }

    @Override
    public void part1() {
        long largestRect = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point b = points.get(j);
                long rect = area(a, b);
                largestRect = Math.max(largestRect, rect);
            }
        }
        printSolution(largestRect);
    }

    @Override
    public void part2() {
        lines = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            lines.add(new Line(points.get(i), points.get((i + 1) % points.size())));
        }

        long largestRect = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point b = points.get(j);
                long rect = area(a, b);
                if (rect > largestRect && isValidRectP2(a, b)) {
                    largestRect = rect;
                }
            }
        }
        printSolution(largestRect);
    }

    private long area(Point a, Point b) {
        long xDiff = Math.abs(a.x - b.x);
        long yDiff = Math.abs(a.y - b.y);
        return (xDiff + 1) * (yDiff + 1);
    }

    private boolean isValidRectP2(Point a, Point b) {
        final int maxX = Math.max(a.x, b.x);
        final int minX = Math.min(a.x, b.x);
        final int maxY = Math.max(a.y, b.y);
        final int minY = Math.min(a.y, b.y);

        return lines.stream().allMatch(line -> {
            boolean leftOf = minX >= Math.max(line.start().x, line.end().x);
            boolean rightOf = maxX <= Math.min(line.start().x, line.end().x);
            boolean above = maxY <= Math.min(line.start().y, line.end().y);
            boolean below = minY >= Math.max(line.start().y, line.end().y);

            return leftOf || rightOf || above || below;
        });
    }
}
