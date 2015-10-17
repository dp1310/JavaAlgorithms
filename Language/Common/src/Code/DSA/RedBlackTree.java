package Code.DSA;

/**
 *
 *
 * @author Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class RedBlackTree {
    private final static Node NIL = new Node(0);
    private int size = 0;
    private Node root;

    static {
        NIL.red = false;
    }

    public RedBlackTree(int t) {
        size = 1;
        root = new Node(t);
        root.red = false;
    }

    public RedBlackTree(int[] ar) {
        this(ar[0]);

        for (int i = 1; i < ar.length; i++)
            add(ar[i]);
    }

    public int size() {
        return size;
    }

    public void add(int value) {
        insert(value);
    }

    private void insert(int value) {
        size++;
        Node temp = root;

        while (true) {
            if (temp.key >= value) {
                if (temp.left == NIL) {
                    temp.left = new Node(value);
                    temp.left.parent = temp;
                    return;
                }
                temp = temp.left;
            } else {
                if (temp.right == NIL) {
                    temp.right = new Node(value);
                    temp.right.parent = temp;
                    return;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        Node node = root;

        while (node != NIL && node.key != value) {
            if (node.key > value)
                node = node.left;
            else
                node = node.right;
        }

        return node != NIL;
    }

    private static void add(Node node, int value) {
        // write code here;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        if (node.right != NIL)
            node.right.parent = node;

        right.parent = node.parent;
        node.parent = right;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        if (node.left != NIL)
            node.left.parent = node;

        left.parent = node.parent;
        node.parent = left;
        return left;
    }

    final static class Node {
        int key;
        boolean red = true;
        Node left = NIL, right = NIL, parent = NIL;

        Node(int value) {
            key = value;
        }
    }
}
