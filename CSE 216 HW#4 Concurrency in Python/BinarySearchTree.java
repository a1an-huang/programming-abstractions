import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
    String name;
    Node<T> root;

    public BinarySearchTree(String name) {
        this.name = name;
    }

    public void addAll(List<T> lst) {
        for (T val : lst) {
            insert(val);
        }
    }

    public void insert(T val) {
        Node<T> newNode = new Node<T>(val);
        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> prev = null;
        Node<T> temp = root;
        while (temp != null) {
            if (temp.getData().compareTo(val) > 0) {
                prev = temp;
                temp = temp.getLeft();
            } else if (temp.getData().compareTo(val) < 0) {
                prev = temp;
                temp = temp.getRight();
            }
        }
        if (prev.getData().compareTo(val) > 0)
            prev.setLeft(newNode);
        else
            prev.setRight(newNode);
    }

    class BstIterator implements Iterator<T> {

        Stack<Node<T>> stack;

        public BstIterator(Node<T> root) {
            stack = new Stack<Node<T>>();

            leftNode(root);
        }

        private void leftNode(Node<T> root) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return stack.size() > 0;
        }

        @Override
        public T next() {
            Node<T> top = stack.pop();
            if (top != null) {
                leftNode(top.getRight());
            }

            return top.getData();
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new BstIterator(root);
    }

    public static <T extends Comparable<T>> List<T> merge(List<BinarySearchTree<T>> bstlist) {
        List<T> lst = new ArrayList<T>();
        for (BinarySearchTree<T> bst : bstlist) {
            Thread thread = new Thread() {
                public void run() {
                    bst.forEach(num -> lst.add(num));
                }
            };
            thread.start();
            try {
                thread.join();
                lst.sort(null);
            } catch (Exception e) {
            }
        }
        return lst;
    }

    /**
     * public static <T extends Comparable<T>> List<T>
     * merge(List<BinarySearchTree<T>> bstlist) {
     * 
     * }
     */

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

        System.out.println(merge(Arrays.asList(t1, t2)));
    }

    public String toString() {
        return "[" + this.name + "] " + root.getData() + " " + stringHelper(root.getLeft(), true) + " "
                + stringHelper(root.getRight(), false);
    }

    public String stringHelper(Node<T> root, boolean isLeft) {
        if (root == null) {
            return "";
        }
        if (root.getLeft() != null && root.getRight() != null) {
            return "L:(" + root.getData() + stringHelper(root.getLeft(), true) + ")" + "R:(" + root.getData()
                    + stringHelper(root.getRight(), false) + ")";
        } else if (root.getRight() == null && isLeft) {
            return " L:(" + root.getData() + stringHelper(root.getLeft(), true) + ")";
        } else if (root.getLeft() == null && !isLeft) {
            return " R:(" + root.getData() + stringHelper(root.getRight(), false) + ")";
        } else if (root.getRight() == null) {
            return " R:(" + root.getData() + stringHelper(root.getLeft(), true) + ")";
        } else if (root.getLeft() == null) {
            return " L:(" + root.getData() + stringHelper(root.getRight(), false) + ")";
        }

        return "" + root.getData();

    }
}
