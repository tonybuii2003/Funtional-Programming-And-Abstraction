import java.util.*;
import java.util.stream.*;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
    private BinarySearchTreeNode<T> root;
    private BinarySearchTreeNode<T> cursor;

    private int size;
    private String name;
    static boolean done = false;

    public BinarySearchTree(String name) {
        this.root = null;
        this.cursor = root;
        this.size = 0;
        this.name = name;
    }

    public BinarySearchTreeNode<T> getCursor() {
        return cursor;
    }

    public void CursorToRoot() {
        cursor = root;
    }

    public void cursorToLeft() {
        this.cursor = cursor.getLeft();
    }

    public void cursorToRight() {
        this.cursor = cursor.getRight();
    }

    public BinarySearchTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinarySearchTreeNode<T> root) {
        this.root = root;
    }

    public void setCursor(BinarySearchTreeNode<T> cursor) {
        this.cursor = cursor;
    }

    public void addAll(List<T> asList) {
        for (T i : asList) {
            ++size;
            add(new BinarySearchTreeNode<T>(i));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return treeAsList(root, new ArrayList<T>()).stream().sorted().collect(Collectors.toList()).iterator();
    }

    public void add(BinarySearchTreeNode<T> newNode) {

        if (root == null) {

            root = newNode;
            cursor = root;

        } else {
            if (cursor.getName().compareTo(newNode.getName()) > 0) {
                if (cursor.getLeft() == null) {
                    cursor.setLeft(newNode);
                    CursorToRoot();
                } else {
                    cursorToLeft();
                    add(newNode);
                }

            }
            if (cursor.getName().compareTo(newNode.getName()) < 0) {
                if (cursor.getRight() == null) {
                    cursor.setRight(newNode);
                    CursorToRoot();
                } else {
                    cursorToRight();
                    add(newNode);
                }

            }

        }

    }

    public List<T> treeAsList(BinarySearchTreeNode<T> tree, List<T> root) {
        root.add(tree.getName());
        if (tree.getLeft() != null) {
            treeAsList(tree.getLeft(), root);
        }
        if (tree.getRight() != null) {
            treeAsList(tree.getRight(), root);
        }
        return root;

    }

    public static <T extends Comparable<T>> List<T> merge(BinarySearchTree<T> t1, BinarySearchTree<T> t2) {
        ArrayList<T> mergedList = new ArrayList<T>();
        Object lock = new Object();

        Thread theThreadOne = new Thread(new Runnable() {
            Iterator<? extends T> lst1 = t1.iterator();

            @Override
            public void run() {

                while (lst1.hasNext()) {
                    synchronized (lock) {
                        lock.notifyAll();
                        if (!done) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        mergedList.add(lst1.next());
                        lock.notifyAll();
                    }
                }
                done = true;

            }
        });
        Thread theThreadTwo = new Thread(new Runnable() {
            Iterator<? extends T> lst2 = t2.iterator();

            @Override
            public void run() {

                while (lst2.hasNext()) {
                    synchronized (lock) {
                        lock.notifyAll();
                        if (!done) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        mergedList.add(lst2.next());
                        lock.notifyAll();
                    }
                }

            }

        });
        theThreadOne.start();
        theThreadTwo.start();
        try {
            theThreadOne.join();
            theThreadTwo.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BinarySearchTree<T> ansTree = new BinarySearchTree<>("");
        ansTree.addAll(mergedList);
        return mergedList.stream().sorted().collect(Collectors.toList());

    }

    public String toString() {
        String tmp = String.format("[%s] %s", name, root.getName());
        List<String> ans = toStringHelper(root, new ArrayList<String>(), tmp, new Stack<String>());
        String strAns = "";
        for (String i : ans) {
            strAns += i;
        }
        return strAns;
    }

    public List<String> toStringHelper(BinarySearchTreeNode<T> root, ArrayList<String> list, String addedString,
            Stack<String> tmpStack) {
        list.add(addedString);
        if (root.getLeft() != null) {

            toStringHelper(root.getLeft(), list, String.format(" L:(%s", root.getLeft().getName()),
                    tmpStack);
            list.add(")");
        }

        if (root.getRight() != null) {

            toStringHelper(root.getRight(), list, String.format(" R:(%s", root.getRight().getName()), tmpStack);
            list.add(")");
        }

        return list;

    }

    public static void main(String... args) {
        // each tree has a name, provided to its constructor
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>("Oak");
        // adds the elements to t1 in the order 5, 3, 0, and then 9
        t1.addAll(Arrays.asList(5, 3, 0, 9));
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>("Maple");
        // adds the elements to t2 in the order 9, 5, and then 10
        t2.addAll(Arrays.asList(9, 5, 10));
        System.out.println(t1); // see the expected output for exact format
        t1.forEach(System.out::println); // iteration in increasing order
        System.out.println(t2); // see the expected output for exact format
        t2.forEach(System.out::println); // iteration in increasing order
        BinarySearchTree<String> t3 = new BinarySearchTree<>("Cornucopia");
        t3.addAll(Arrays.asList("coconut", "apple", "banana", "plum", "durian",
                "no durians on this tree!", "tamarind"));
        System.out.println(t3); // see the expected output for exact format

        t3.forEach(System.out::println); // iteration in increasing order
        BinarySearchTree<String> t4 = new BinarySearchTree<>("Cornucopia");
        t4.addAll(Arrays.asList("apple"));
        List<String> mergedList = merge(t3, t4);
        for (String i : mergedList) {
            System.out.print(i + " ");
        }

    }
}

class BinarySearchTreeNode<T> {
    private T name;
    private BinarySearchTreeNode<T> left, right;

    public BinarySearchTreeNode(T name) {
        this.left = null;
        this.right = null;
        this.name = name;

    }

    public T getName() {
        return name;
    }

    public BinarySearchTreeNode<T> getLeft() {
        return left;
    }

    public BinarySearchTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinarySearchTreeNode<T> right) {
        this.right = right;
    }

    public void setLeft(BinarySearchTreeNode<T> left) {
        this.left = left;
    }

    public void setName(T name) {
        this.name = name;
    }
}
