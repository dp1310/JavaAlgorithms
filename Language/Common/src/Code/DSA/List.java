package Code.DSA;

import java.util.Iterator;

/**
 * List class to support list related questions.
 * @author Ashok Rajpurohit ashok1113@gmail.com
 */
public class List<T> implements Iterable {
    Node<T> head, tail;
    int size = 0;

    public List() {
        // do nothing
    }

    public List(T t) {
        head = new Node<T>(t);
        tail = head;
        size = 1;
    }

    List(T[] ar) {
        if (ar.length == 0)
            throw new NullPointerException("Bhai Empty array hai");

        size = ar.length;
        head = new Node<T>(ar[0]);
        tail = head;
        for (int i = 1; i < ar.length; i++) {
            tail.next = new Node<T>(ar[i]);
            tail = tail.next;
        }
    }

    public void add(T t) {
        if (head == null) {
            size = 1;
            head = new Node<T>(t);
            tail = head;
            return;
        }
        size++;
        tail.next = new Node<T>(t);
        tail = tail.next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T getHead() {
        return head.data;
    }

    public T getLast() {
        return tail.data;
    }

    public Iterator<Node<T>> iterator() {
        return new Node<T>(null, head);
    }
}
