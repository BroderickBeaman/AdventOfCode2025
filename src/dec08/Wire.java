package dec08;

public record Wire(Point3D left, Point3D right, double length) implements Comparable<Wire> {

    public Wire(Point3D left, Point3D right) {
        this(left, right, computeLength(left, right));
    }

    private static double computeLength(Point3D left, Point3D right) {
        double xDiff = left.x() - right.x();
        double yDiff = left.y() - right.y();
        double zDiff = left.z() - right.z();
        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2) + Math.pow(zDiff, 2));
    }

    @Override
    public int compareTo(Wire o) {
        return Double.compare(this.length, o.length);
    }
}
