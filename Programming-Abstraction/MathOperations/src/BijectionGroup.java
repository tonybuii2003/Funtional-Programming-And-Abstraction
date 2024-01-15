import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;

public class BijectionGroup<T> {

    // your methods go here
    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        List<List<T>> ans = new ArrayList<>();
        List<T> list = new ArrayList<T>(domain);
        helperMethod(list, 0, domain.size() - 1, ans);
        return ans.stream().map(l -> (Function<T, T>) t -> l.get(list.indexOf(t))).collect(Collectors.toSet());

    }

    public static <T> Group<Function<T, T>> bijectionGroup(Set<T> items) {
        Group<Function<T, T>> ansClass = new Group<Function<T, T>>() {
            @Override
            public Function<T, T> binaryOperation(Function<T, T> one, Function<T, T> other) {
                // TODO Auto-generated method stub
                return one.andThen(other);
            }

            @Override
            public Function<T, T> identity() {
                // TODO Auto-generated method stub
                return Function.identity();
            }

            @Override
            public Function<T, T> inverseOf(Function<T, T> t) {
                // TODO Auto-generated method stub\

                return t.andThen(t);
            }

        };
        return ansClass;
    }

    public static <T> List<T> swap(List<T> list, int i, int j) {

        List<T> ans = new ArrayList<T>();
        for (T e : list)
            ans.add(e);
        T tmp = ans.get(i);
        ans.set(i, ans.get(j));
        ans.set(j, tmp);
        return ans;

    }

    public static <T> void helperMethod(List<T> domain, int l, int r, List<List<T>> ans) {
        if (r == l) {
            ans.add(new ArrayList<T>(domain));
            return;
        } else {
            for (int i = l; i <= r; i++) {
                List<T> newSet = swap(domain, l, i);
                helperMethod(newSet, l + 1, r, ans);
            }
        }
    }

    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

        // you have to figure out the data types in the lines below
        Group<Function<Integer, Integer>> g = bijectionGroup(a_few);
        Function<Integer, Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
        Function<Integer, Integer> f3 = bijectionsOf(a_few).stream().reduce((a, b) -> b).orElse(null);
        Function<Integer, Integer> f2 = g.inverseOf(f3);
        Function<Integer, Integer> f4 = g.inverseOf(f2);
        Function<Integer, Integer> test = g.binaryOperation(f3, f2);
        Function<Integer, Integer> id = g.identity();
        System.out.println("f1: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f1.apply(n)));
        System.out.println("\nid: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, id.apply(n)));
        System.out.println("\nf2: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f2.apply(n)));
        System.out.println("\nf3: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f3.apply(n)));
        System.out.println("\nf4: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f4.apply(n)));
        System.out.println("\ntest: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, test.apply(n)));
    }

}
