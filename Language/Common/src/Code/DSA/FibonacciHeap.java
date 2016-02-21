package Code.DSA;

/**
 * This class is implementation of Fibonacci Heap.
 * Currently the key and value both are considered integer.
 * In Future I may write for generic type of Key and Value
 * to support different data type.
 * For Reference please follow Fibonacci Heaps from Introduction
 * to Algorithms by CLRS.
 *
 * @author Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class FibonacciHeap {
    private Node root;

    public FibonacciHeap() {
        root = null;
    }

    public void add(int key, int value) {
        // write code
        if (root == null) {
            root = new Node(key, value);
            return;
        }

        root.add(new Node(key, value));
        if (root.key > root.right.key) {
            root = root.right;
        }
    }

    public int getMin() {
        return root.value;
    }

    private final static class Node {
        int key;
        int value;
        Node left, right, parent, child;

        Node(int k, int v) {
            key = k;
            value = v;
            left = this;
            right = this;
        }

        void add(Node node) {
            node.right = this.right;
            node.left = this;
            node.parent = this.parent;
            this.right.left = node;
            this.right = node;
        }
    }
}
