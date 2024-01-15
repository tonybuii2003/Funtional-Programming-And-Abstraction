package geometry;

import java.util.Comparator;

public class DegreesComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return Double.compare(o1.getDegrees(), o2.getDegrees());
    }
}
