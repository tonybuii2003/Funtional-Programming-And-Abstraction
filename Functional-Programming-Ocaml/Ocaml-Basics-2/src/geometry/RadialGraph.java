package geometry;

import java.util.*;

public class RadialGraph extends Shape {
    private Point center;
    private List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) {
        double firstLength = Math
                .sqrt((center.getX() - neighbors.get(0).getX()) * (center.getX() - neighbors.get(0).getX())
                        + (center.getY() - neighbors.get(0).getY()) * (center.getY() - neighbors.get(0).getY()));
        for (int i = 1; i < neighbors.size(); i++) {
            double thisLength = Math
                    .sqrt((center.getX() - neighbors.get(i).getX()) * (center.getX() - neighbors.get(i).getX())
                            + (center.getY() - neighbors.get(i).getY()) * (center.getY() - neighbors.get(i).getY()));
            if (firstLength != thisLength) {
                throw new IllegalArgumentException("edges need to be the same length");
            }
        }
        this.center = center;
        this.neighbors = neighbors;
    }

    protected Point getCenter() {
        return center;
    }

    protected List<Point> getNeighbors() {
        return neighbors;
    }

    protected RadialGraph(Point center) {
        this.center = center;
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        Point[] tmpList = new Point[neighbors.size()];
        for (int i = 0; i < tmpList.length; i++) {
            tmpList[i] = neighbors.get(i);
        }
        RadialGraph ans = new RadialGraph(center, Arrays.asList(tmpList));
        Point tmp = ans.center;
        ans = ans.translateBy(-ans.center.getX(), -ans.center.getY());
        for (int i = 0; i < ans.getNeighbors().size(); i++) {
            Point point = ans.neighbors.get(i);
            Point newPoint = new Point(point.getName(),
                    Math.round((point.getX() * Math.cos(Math.toRadians(degrees))
                            - point.getY() * Math.sin(Math.toRadians(degrees))) * 100.0) / 100.0,
                    Math.round((point.getX() * Math.sin(Math.toRadians(degrees))
                            + point.getY() * Math.cos(Math.toRadians(degrees))) * 100.0) / 100.0);
            ans.getNeighbors().set(i, newPoint);
        }
        ans = ans.translateBy(tmp.getX(), tmp.getY());
        return ans;

    }

    @Override
    public RadialGraph translateBy(double x, double y) {

        Point[] tmpList = new Point[neighbors.size()];
        for (int i = 0; i < tmpList.length; i++) {
            tmpList[i] = neighbors.get(i);
        }
        RadialGraph ans = new RadialGraph(center, Arrays.asList(tmpList));
        if (neighbors != null) {
            Point newCenter = new Point(ans.center.getName(), ans.center.getX() + x, ans.center.getY() + y);
            ans.center = newCenter;
            for (int i = 0; i < ans.neighbors.size(); i++) {
                Point point = ans.neighbors.get(i);
                Point newPoint = new Point(point.getName(), point.getX() + x, point.getY() + y);
                ans.neighbors.set(i, newPoint);
            }
        } else {
            Point newCenter = new Point(center.getName(), center.getX() + x, center.getY() + y);
            ans.center = newCenter;
        }

        return ans;
    }

    @Override
    public boolean equals(Object obj) {
        boolean ans = false;
        RadialGraph tmp = (RadialGraph) obj;
        for (int i = 0; i < neighbors.size(); i++) {
            if (this.neighbors.get(i).equals(tmp.neighbors.get(i))) {
                ans = true;
            }
        }
        return ans;
    }

    @Override
    public String toString() {
        String str = "";
        str += String.format("[%s", center.toString());
        if (neighbors != null) {
            Collections.sort(neighbors, new DegreesComparator());
            for (Point i : neighbors) {
                str += String.format("; %s", i.toString());
            }
        }
        return str + "]";
    }

    @Override
    public Point center() {
        return center;
    }

    /*
     * Driver method given to you as an outline for testing your code. You can
     * modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as
     * they are (some lines have additional
     * explanation of what is expected)
     */
    public static void main(String... args) {
        Point center = new Point("center", 0, 0);
        Point east = new Point("east", 1, 0);
        Point west = new Point("west", -1, 0);
        Point north = new Point("north", 0, 1);
        Point south = new Point("south", 0, -1);
        Point toofarsouth = new Point("south", 0, -2);

        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);

        // Must print: [(center, 0.0, 0.0)]
        System.out.println(lonely);

        // This line must throw IllegalArgumentException, since the edges will not be of
        // the same length
        // RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth,
        // east, west));

        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

        // Must follow the documentation in the Shape abstract class, and print the
        // following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0);
        // (south, 0.0, -1.0)]
        System.out.println(g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1,
        // 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
        g = g.rotateBy(90);
        System.out.println("After rotation: ");
        System.out.println(g);
        // you should similarly add tests for the translateBy(x, y) method
        g = g.translateBy(0.5, 0.5);
        System.out.println("After translate: ");
        System.out.println(g);
    }
}
