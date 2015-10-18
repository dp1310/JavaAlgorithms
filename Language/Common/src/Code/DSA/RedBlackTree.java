package Code.DSA;

/**
 * This is implementation of Red-Black Tree as explained in Introduction
 * to Algorithms by CLRS. Please refer the same for more details.
 *
 * @author Ashok Rajpurohit (ashok1113@gmail.com)
 * @see     Code.DSA.BSTAVL
 * @see     Code.DSA.BSTbyNode
 * @see     Code.DSA.RankTree
 */
public class RedBlackTree {
    private final static Node NIL = new Node(0);
    private final static boolean RED = true, BLACK = false;
    private int size = 0;
    private Node root;

    static {
        NIL.color = BLACK;
    }

    public RedBlackTree(int t) {
        size = 1;
        root = new Node(t);
        root.color = BLACK;
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
        Node node = insert(value);
        insertFix(node);
        root.color = BLACK;
    }

    private Node insert(int value) {
        size++;
        Node temp = root;

        while (true) {
            if (temp.key >= value) {
                if (temp.left == NIL) {
                    temp.left = new Node(value);
                    temp.left.parent = temp;
                    return temp.left;
                }
                temp = temp.left;
            } else {
                if (temp.right == NIL) {
                    temp.right = new Node(value);
                    temp.right.parent = temp;
                    return temp.right;
                }
                temp = temp.right;
            }
        }
    }

    private void insertFix(Node node) {
        while (node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) {
                Node tau = node.parent.parent.right;

                if (tau.color == RED) {
                    node.parent.color = BLACK;
                    tau.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else if (node == node.parent.right) {
                    node = node.parent;
                    rotateLeft(node);
                } else if (node == node.parent.left) {
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent);
                }
            } else {
                Node chacha = node.parent.parent.left;

                if (chacha.color == RED) {
                    node.parent.color = BLACK;
                    chacha.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else if (node == node.parent.right) {
                    node = node.parent;
                    rotateLeft(node);
                } else if (node == node.parent.left) {
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent);
                }
            }
        }
    }

    public boolean contains(int value) {
        return find(value) != NIL;
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

    public void remove(int value) {
        Node node = find(value);

        if (node != NIL)
            delete(node);
    }

    private void delete(Node node) {
        Node copy = node, fixUp = NIL;
        boolean copy_red = copy.color;

        if (node.left == NIL) {
            fixUp = node.right;
            replace(node, node.right);
        } else if (node.right == NIL) {
            fixUp = node.left;
            replace(node, node.left);
        } else {
            copy = findMin(node.right);
            copy_red = copy.color;
            fixUp = copy.right;

            if (copy.parent == node)
                fixUp.parent = copy;
            else {
                replace(copy, copy.right);
                copy.right = node.right;
                copy.right.parent = copy;
            }

            replace(node, copy);
            copy.left = node.left;
            copy.left.parent = copy;
            copy.color = node.color;
        }

        if (!copy_red)
            deleteFix(fixUp);
    }

    private Node findMin(Node node) {
        while (node.left != NIL)
            node = node.left;

        return node;
    }

    private void deleteFix(Node node) {
        while (node != root && node.color == BLACK) {
            if (node == node.parent.left) {
                Node temp = node.parent.right;
                if (temp.color == RED) {
                    temp.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    temp = node.parent.right;
                }

                if (temp.left.color == BLACK && temp.right.color == BLACK) {
                    temp.color = RED;
                    node = node.parent;
                } else {
                    if (temp.right.color == BLACK) {
                        temp.left.color = BLACK;
                        temp.color = RED;
                        rotateRight(temp);
                        temp = node.parent.right;
                    }

                    temp.color = node.parent.color;
                    node.parent.color = BLACK;
                    temp.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                Node temp = node.parent.left;
                if (temp.color == RED) {
                    temp.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    temp = node.parent.left;
                }

                if (temp.left.color == BLACK && temp.right.color == BLACK) {
                    temp.color = RED;
                    node = node.parent;
                } else {
                    if (temp.left.color == BLACK) {
                        temp.right.color = BLACK;
                        temp.color = RED;
                        rotateLeft(temp);
                        temp = node.parent.left;
                    }

                    temp.color = node.parent.color;
                    node.parent.color = BLACK;
                    temp.left.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    private void replace(Node u, Node v) {
        if (u.parent == NIL)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;

        v.parent = u.parent;
    }

    private Node find(int value) {
        Node temp = root;

        while (temp != NIL && temp.key != value) {
            if (temp.key > value)
                temp = temp.left;
            else
                temp = temp.right;
        }
        return temp;
    }

    final static class Node {
        int key;
        boolean color = RED;
        Node left = NIL, right = NIL, parent = NIL;

        Node(int value) {
            key = value;
        }
    }
}
