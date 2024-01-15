package geometry;

import java.util.*;

public class Square extends Shape {
        private Point a, b, c, d;

        protected Point getA() {
                return a;
        }

        protected Point getB() {
                return b;
        }

        protected Point getC() {
                return c;
        }

        protected Point getD() {
                return d;
        }

        protected void setA(Point a) {
                this.a = a;
        }

        protected void setB(Point b) {
                this.b = b;
        }

        protected void setC(Point c) {
                this.c = c;
        }

        protected void setD(Point d) {
                this.d = d;
        }

        @Override
        public Point center() {
                Point ans = new Point("center", (a.getX() + c.getX()) / 2, (a.getY() + c.getY()) / 2);
                return ans;
        }

        @Override
        public boolean equals(Object obj) {
                if (!(obj instanceof Square)) {
                        return false;
                }
                Square tmp = (Square) obj;
                if (tmp.getA().equals(this.a) && tmp.getB().equals(this.b) && tmp.getC().equals(this.c)
                                && tmp.getD().equals(this.d)) {
                        return true;
                }
                return false;
        }

        @Override
        public Square rotateBy(int degrees) {
                Square ans = new Square(a, b, c, d);
                Point tmp = ans.center();
                ans = (Square) translateBy(-ans.center().getX(), -ans.center().getY());
                Point point = ans.getA();
                Point newPoint = new Point(point.getName(),
                                Math.round((point.getX() * Math.cos(Math.toRadians(degrees))
                                                - point.getY() * Math.sin(Math.toRadians(degrees))) * 100.0) / 100.0,
                                Math.round((point.getX() * Math.sin(Math.toRadians(degrees))
                                                + point.getY() * Math.cos(Math.toRadians(degrees))) * 100.0) / 100.0);
                ans.a = newPoint;
                point = ans.getB();
                newPoint = new Point(point.getName(),
                                Math.round((point.getX() * Math.cos(Math.toRadians(degrees))
                                                - point.getY() * Math.sin(Math.toRadians(degrees))) * 100.0) / 100.0,
                                Math.round((point.getX() * Math.sin(Math.toRadians(degrees))
                                                + point.getY() * Math.cos(Math.toRadians(degrees))) * 100.0) / 100.0);
                ans.b = newPoint;
                point = ans.getC();
                newPoint = new Point(point.getName(),
                                Math.round((point.getX() * Math.cos(Math.toRadians(degrees))
                                                - point.getY() * Math.sin(Math.toRadians(degrees))) * 100.0) / 100.0,
                                Math.round((point.getX() * Math.sin(Math.toRadians(degrees))
                                                + point.getY() * Math.cos(Math.toRadians(degrees))) * 100.0) / 100.0);
                ans.c = newPoint;
                point = ans.getD();
                newPoint = new Point(point.getName(),
                                Math.round((point.getX() * Math.cos(Math.toRadians(degrees))
                                                - point.getY() * Math.sin(Math.toRadians(degrees))) * 100.0) / 100.0,
                                Math.round((point.getX() * Math.sin(Math.toRadians(degrees))
                                                + point.getY() * Math.cos(Math.toRadians(degrees))) * 100.0) / 100.0);
                ans.d = newPoint;
                ans = (Square) ans.translateBy(tmp.getX(), tmp.getY());
                return ans;
        }

        @Override
        public Shape translateBy(double x, double y) {
                Square ans = new Square(a, b, c, d);
                Point point = ans.getA();
                Point newPoint = new Point(point.getName(), point.getX() + x, point.getY() + y);
                ans.a = newPoint;
                point = ans.getB();
                newPoint = new Point(point.getName(), point.getX() + x, point.getY() + y);
                ans.b = newPoint;
                point = ans.getC();
                newPoint = new Point(point.getName(), point.getX() + x, point.getY() + y);
                ans.c = newPoint;
                point = ans.getD();
                newPoint = new Point(point.getName(), point.getX() + x, point.getY() + y);
                ans.d = newPoint;

                return ans;
        }

        @Override
        public String toString() {
                String str = "";
                Square ans = new Square(a, b, c, d);
                Point centerTmp = ans.center();
                ans = (Square) ans.translateBy(-ans.center().getX(), -ans.center().getY());
                List<Point> points = new ArrayList<Point>();
                points.add(ans.a);
                points.add(ans.b);
                points.add(ans.c);
                points.add(ans.d);
                Collections.sort(points, new DegreesComparator());
                str += "[" + new Point(centerTmp.getName(), ans.center().getX() + centerTmp.getX(),
                                ans.center().getY() + centerTmp.getY()) + "; ";
                for (Point i : points) {
                        Point translatePoint = new Point(i.getName(), i.getX() + centerTmp.getX(),
                                        i.getY() + centerTmp.getY());
                        str += String.format("%s; ", translatePoint.toString());
                }
                return str.substring(0, str.length() - 2) + "]";
        }

        public Square(Point a, Point b, Point c, Point d) {
                double lengthAB = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
                double lengthAD = Math.sqrt(Math.pow(a.getX() - d.getX(), 2) + Math.pow(a.getY() - d.getY(), 2));
                double lengthCB = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
                double lengthCD = Math.sqrt(Math.pow(c.getX() - d.getX(), 2) + Math.pow(c.getY() - d.getY(), 2));
                double lengthBD = Math.sqrt(Math.pow(b.getX() - d.getX(), 2) + Math.pow(b.getY() - d.getY(), 2));
                boolean conditionOne = lengthAB == lengthAD && lengthAB == lengthCB && lengthAB == lengthCD;
                double degreesOne = Math
                                .acos((lengthAB * lengthAB + lengthAD * lengthAD - lengthBD * lengthBD)
                                                / (2 * lengthAB * lengthAD));
                double degreesTwo = Math
                                .acos((lengthCB * lengthCB + lengthCD * lengthCD - lengthBD * lengthBD)
                                                / (2 * lengthCB * lengthCD));
                boolean conditionTwo = Math.toDegrees(degreesTwo) == Math.toDegrees(degreesOne);
                if (!(conditionOne && conditionTwo))
                        throw new IllegalArgumentException("This is not a square");
                this.a = a;
                this.b = b;
                this.c = c;
                this.d = d;
        }

        public static void main(String... args) {
                Point A = new Point("A", 3, 3);
                Point B = new Point("B", 2, 3);
                Point C = new Point("C", 2, 2);
                Point D = new Point("D", 3, 2);
                Square aSquare = new Square(A, B, C, D);
                System.out.println(aSquare);
                System.out.printf("After rotate 45 degrees: %s\n", aSquare.rotateBy(45));
                System.out.printf("After rotate 90 degrees: %s\n", aSquare.rotateBy(90));
                System.out.printf("After rotate 180 degrees: %s\n", aSquare.rotateBy(180));
                System.out.printf("After rotate 270 degrees: %s\n", aSquare.rotateBy(270));
                System.out.printf("After translate (1,1): %s\n", aSquare.translateBy(1, 1));

                System.out.println("The center: " + aSquare.center());
        }
}
