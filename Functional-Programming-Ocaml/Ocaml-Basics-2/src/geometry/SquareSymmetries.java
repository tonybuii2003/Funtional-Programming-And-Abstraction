package geometry;

import java.util.ArrayList;
import java.util.Collection;

public class SquareSymmetries implements Symmetries<Square> {
    public SquareSymmetries() {

    }

    @Override
    public boolean areSymmetric(Square s1, Square s2) {
        Collection<Square> symmetries = symmetriesOf(s1);
        for (Square i : symmetries) {
            if (i.equals(s2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Square> symmetriesOf(Square s) {
        Collection<Square> ans = new ArrayList<Square>();
        // 1 Itself
        ans.add(s);
        // 2 rotate 90 degrees
        ans.add(s.rotateBy(90));
        // 3 rotate 180 degrees
        ans.add(s.rotateBy(180));
        // 4 rotate 270 degrees
        ans.add(s.rotateBy(270));
        // 5 vertical reflection
        Square tmp = s;
        tmp.translateBy(-tmp.center().getX(), -tmp.center().getY());
        tmp.setA(new Point(tmp.getA().getName(), -tmp.getA().getX(), tmp.getA().getY()));
        tmp.setB(new Point(tmp.getB().getName(), -tmp.getB().getX(), tmp.getB().getY()));
        tmp.setC(new Point(tmp.getC().getName(), -tmp.getC().getX(), tmp.getC().getY()));
        tmp.setD(new Point(tmp.getD().getName(), -tmp.getD().getX(), tmp.getD().getY()));
        ans.add(tmp);
        // 6 horizontal reflection
        tmp = s;
        tmp.translateBy(-tmp.center().getX(), -tmp.center().getY());
        tmp.setA(new Point(tmp.getA().getName(), tmp.getA().getX(), -tmp.getA().getY()));
        tmp.setB(new Point(tmp.getB().getName(), tmp.getB().getX(), -tmp.getB().getY()));
        tmp.setC(new Point(tmp.getC().getName(), tmp.getC().getX(), -tmp.getC().getY()));
        tmp.setD(new Point(tmp.getD().getName(), tmp.getD().getX(), -tmp.getD().getY()));
        ans.add(tmp);
        // 7 diagonal reflection
        tmp = s;
        tmp.translateBy(-tmp.center().getX(), -tmp.center().getY());
        tmp.rotateBy(45);
        tmp.setA(new Point(tmp.getA().getName(), tmp.getA().getX(), -tmp.getA().getY()));
        tmp.setB(new Point(tmp.getB().getName(), tmp.getB().getX(), -tmp.getB().getY()));
        tmp.setC(new Point(tmp.getC().getName(), tmp.getC().getX(), -tmp.getC().getY()));
        tmp.setD(new Point(tmp.getD().getName(), tmp.getD().getX(), -tmp.getD().getY()));
        ans.add(tmp);
        // 8 counter diagonal reflection
        tmp = s;
        tmp.translateBy(-tmp.center().getX(), -tmp.center().getY());
        tmp.rotateBy(45);
        tmp.setA(new Point(tmp.getA().getName(), -tmp.getA().getX(), tmp.getA().getY()));
        tmp.setB(new Point(tmp.getB().getName(), -tmp.getB().getX(), tmp.getB().getY()));
        tmp.setC(new Point(tmp.getC().getName(), -tmp.getC().getX(), tmp.getC().getY()));
        tmp.setD(new Point(tmp.getD().getName(), -tmp.getD().getX(), tmp.getD().getY()));
        ans.add(tmp);
        return ans;
    }

}
