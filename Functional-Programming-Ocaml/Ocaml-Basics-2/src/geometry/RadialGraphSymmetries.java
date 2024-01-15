package geometry;

import java.util.ArrayList;
import java.util.Collection;

public class RadialGraphSymmetries implements Symmetries<RadialGraph> {
    public RadialGraphSymmetries() {
    }

    @Override
    public boolean areSymmetric(RadialGraph s1, RadialGraph s2) {
        Collection<RadialGraph> symmetries = symmetriesOf(s1);
        for (RadialGraph i : symmetries) {
            if (i.equals(s2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<RadialGraph> symmetriesOf(RadialGraph s) {
        int n = s.getNeighbors().size();
        int degrees = 360 / n;
        int rotate = degrees;
        Collection<RadialGraph> ans = new ArrayList<RadialGraph>();
        while (rotate <= 360) {
            RadialGraph tmp = s.rotateBy(rotate);
            ans.add(tmp);
            rotate += degrees;
        }
        return ans;
    }

}
