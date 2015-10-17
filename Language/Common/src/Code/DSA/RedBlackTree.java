package Code.DSA;

public class RedBlackTree<T> {
    private int size = 0;
    private Node<T> root;

    public RedBlackTree(T t) {
        size = 1;
        root = new Node<T>(t);
    }

    public RedBlackTree(T[] ar) {
        size = 1;
        root = new Node<T>(ar[0]);

        for (int i = 1; i < ar.length; i++)
            add(ar[i]);
    }

    public int size() {
        return size;
    }

    public void add(T value) {
        size++;
        // write code
    }

    private static void add(Node node, int value) {
        // write code here;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        if (node.right != null)
            node.right.parent = node;

        right.parent = node.parent;
        node.parent = right;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        if (node.left != null)
            node.left.parent = node;

        left.parent = node.parent;
        node.parent = left;
        return left;
    }

    final static class Node<T> {
        T key;
        boolean red;
        Node left, right, parent;

        Node(T value) {
            key = value;
        }
    }
}
