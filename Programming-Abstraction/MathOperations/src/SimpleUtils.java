import java.util.Collection;
import java.util.stream.Collectors;
import java.util.*;

public class SimpleUtils {
    // 1. Simple functional programming in a single method chain
    /**
     * Find and return the least element from a collection of given elements that
     * are comparable.
     *
     * @param items:      the given collection of elements
     * @param from_start: a <code>boolean</code> flag that decides how ties are
     *                    broken.
     *                    If <code>true</code>, the element encountered earlier in
     *                    the
     *                    iteration is returned, otherwise the later element is
     *                    returned.
     * @param <T>:        the type parameter of the collection (i.e., the items are
     *                    all of type T).
     * @return the least element in <code>items</code>, where ties are
     *         broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {

        return from_start ? items.stream()
                .reduce((a, b) -> a.compareTo(b) <= 0 ? a : b)
                .orElse(null)
                : items.stream()
                        .reduce((a, b) -> a.compareTo(b) >= 0 ? b : a)
                        .orElse(null);

    }

    /**
     * Flattens a map to a sequence of <code>String</code>s, where each element in
     * the list is formatted
     * as "key -> value" (i.e., each key-value pair is converted to a string in this
     * specific format).
     *
     * @param aMap the specified input map.
     * @param <K>  the type parameter of keys in <code>aMap</code>.
     * @param <V>  the type parameter of values in <code>aMap</code>.
     * @return the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        return aMap.entrySet().stream().map(element -> element.getKey() + " -> " + element.getValue())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String a = least(Arrays.asList(1, 1, 3, 4, 5), true).toString();
        String b = least(Arrays.asList(1, 1, 3, 4, 5), false).toString();
        System.out.println(a == b);
        HashMap<String, String> heroesVSVillains = new HashMap<String, String>() {
            {
                put("Batman", "Joker");
                put("Superman", "DarkSeid");
                put("Goku", "Vegeta");
                put("SuperHeros", "Villans");
            }
        };
        String problem2 = flatten(heroesVSVillains).toString();
        System.out.println(problem2);
    }
}
