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

    public int getSize() {
        return size;
    }

    public void add(T value) {
        size++;
        // write code
    }

    private static void add(Node<T> node, int value) {
        // write code here;
    }


    final static class Node<T> {
        T key;
        boolean red;
        Node left, right;

        Node(T value) {
            key = value;
        }
    }
}
