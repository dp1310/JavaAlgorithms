package Code.DSA;

import java.util.Comparator;

/**
 * *************************WORK IN PROGRESS****************************
 *
 * This class is implementation of Fibonacci Heap.
 * For Reference please follow Fibonacci Heaps from Introduction
 * to Algorithms by CLRS.
 *
 * @author Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class FibonacciHeap<K, V> {
    private List rootList = null;
    private final Comparator<? super K> comparator;
    private int size = 0;

    public FibonacciHeap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    public void add(K key, V value) {
        size++;

        if (rootList == null) {
            rootList = new List();
        }

        rootList.add(new Node(key, value));
    }

    private int compare(Node k1, Node k2) {
        return comparator.compare(k1.key, k2.key);
    }

    public K getMinKey() {
        return rootList.min.key;
    }

    public V getMinKeyValue() {
        return rootList.min.value;
    }

    /**
     * This will merge the parameter heap into this heap and remove elements
     * from the second one so no update can be done to the second heap.
     *
     * @param fibonacciHeap
     */
    public void merge(FibonacciHeap<K, V> fibonacciHeap) {
        if (rootList == null)
            rootList = fibonacciHeap.rootList;
        else
            rootList.merge(fibonacciHeap.rootList);

        size += fibonacciHeap.size;
        delete(fibonacciHeap);
    }

    /**
     * Removes the elements from the fibonacci heap to make it un-usable.
     * When we merge two heaps we don't want any operation performed on the
     * second heap affects the merger heap, So all the connections to old
     * elements has to wiped out.
     *
     * @param fibonacciHeap
     */
    private static void delete(FibonacciHeap<K, V> fibonacciHeap) {
        fibonacciHeap.rootList = null;
        fibonacciHeap.size = 0;
        fibonacciHeap.comparator = null;
    }

    final class Node {
        K key;
        V value;
        Node next, prev;
        List child;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = this;
            prev = this;
        }

        void add(Node node) {
            node.next = this.next;
            node.prev = this;
            this.next.prev = node;
            this.next = node;
        }

        public int compareTo(Node node) {
            return compare(this, node);
        }
    }

    final class List {
        Node min = null, parent = null;
        int size = 0;

        void add(Node node) {
            size++;

            if (min == null) {
                min = node;
                return;
            }

            min.add(node);
            if (compare(min, node) < 0)
                min = node;
        }

        /**
         * Deletes the specified node from the list. It is assumed that the
         * node exists in the list, So any checks to performe it's existance
         * are ignored currently.
         *
         * @param node
         */
        void delete(Node node) {
            if (size < 2) {
                min = null;
                size = 0;
                return;
            }

            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;

            if (node == min) {
                min = node.next;
                reset();
            }
        }

        /**
         * Resets the minimum key node for the list.
         *
         */
        void reset() {
            if (size < 2)
                return;

            Node iter = min.next, ref = min;
            while (iter != ref) {
                if (compare(iter, min) < 0)
                    min = iter;

                iter = iter.next;
            }
        }

        void merge(List list) {
            if (list == null)
                return;

            size += list.size;

            min.next.prev = list.min.prev;
            list.min.next = this.min.next;
            this.min.next = list.min;
            list.min.prev = this.min;

            if (compare(this.min, list.min) > 0)
                min = list.min;
        }
    }
}
