package DSA;

/**
 * Implementation of Range Query Lazy Updation.
 * This kind of Range query is useful when the update is for an index range.
 *
 * @author Ashok Rajpurohit ashok1113@gmail.com
 */
public class RangeQueryLazy {
    private Node root;

    public RangeQueryLazy(int[] ar) {
        construct(ar);
    }

    public void update(int l, int r, int data) {
        update(root, l, r, data);
    }

    public int query(int L, int R) {
        return query(root, L, R);
    }

    private static void update(Node root, int L, int R, int data) {
        if (root.l == L && root.r == R) {
            root.udata += data;
            root.data += data;
            return;
        }
        int mid = (root.l + root.r) >>> 1;
        if (L > mid) {
            update(root.right, L, R, root.udata + data);
            root.left.udata += root.udata + data;
            root.left.data += root.udata + data;
            root.udata = 0;
            root.data = Math.min(root.left.data, root.right.data);
            return;
        }
        if (R <= mid) {
            update(root.left, L, R, root.udata + data);
            root.right.udata += root.udata;
            root.right.data += root.udata;
            root.udata = 0;
            root.data = Math.min(root.left.data, root.right.data);
            return;
        }
        update(root.left, L, mid, root.udata + data);
        update(root.right, mid + 1, R, root.udata + data);
        root.data = Math.min(root.left.data, root.right.data);
        root.udata = 0;
    }

    private static int query(Node root, int L, int R) {
        if (root.l == L && root.r == R)
            return root.data;
        int mid = (root.l + root.r) >>> 1;
        if (L > mid) {
            return root.udata + query(root.right, L, R);
        } else if (R <= mid) {
            return root.udata + query(root.left, L, R);
        }
        return root.udata +
            Math.min(query(root.left, L, mid), query(root.right, mid + 1, R));
    }

    private void construct(int[] ar) {
        root = new Node(0, ar.length - 1, 0);
        int mid = (ar.length - 1) >>> 1;
        root.left = construct(ar, 0, mid);
        root.right = construct(ar, mid + 1, ar.length - 1);
    }

    private Node construct(int[] ar, int l, int r) {
        if (l == r)
            return new Node(l, l, ar[l]);

        Node temp = new Node(l, r, 0);
        int mid = (l + r) >>> 1;
        temp.left = construct(ar, l, mid);
        temp.right = construct(ar, mid + 1, r);
        temp.data = Math.min(temp.left.data, temp.right.data);
        return temp;
    }

    private final static class Node {
        Node left, right;
        int l, r, data, udata;
        boolean update = false;

        Node(int i, int j, int d) {
            l = i;
            r = j;
            data = d;
        }
    }
}
