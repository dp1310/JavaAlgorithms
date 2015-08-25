package Code.Graph;

import java.util.Iterator;

public class Graph {
    List[] list;

    public Graph(int n) {
        //        List<Integer> dum = new List<Integer>();
        list = new List[n];
    }

    public void addEdge(int from, int to) {
        list[from].add(to);
    }

    public void addEdge(int[] from, int[] to) {
        for (int i = 0; i < from.length; i++)
            list[from[i]].add(to[i]);
    }

    final static class List implements Iterable {
        Node head, tail;
        int size = 0;

        public List() {
            // do nothing
        }

        public List(int t) {
            head = new Node(t);
            tail = head;
            size = 1;
        }

        List(int[] ar) {
            if (ar.length == 0)
                throw new NullPointerException("Bhai Empty array hai");

            size = ar.length;
            head = new Node(ar[0]);
            tail = head;
            for (int i = 1; i < ar.length; i++) {
                tail.next = new Node(ar[i]);
                tail = tail.next;
            }
        }

        public void add(int t) {
            if (head == null) {
                size = 1;
                head = new Node(t);
                tail = head;
                return;
            }
            size++;
            tail.next = new Node(t);
            tail = tail.next;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public Node getHead() {
            return head;
        }

        public Iterator<Node> iterator() {
            return new Node(0, head);
        }
    }

    final static class Node implements Iterator {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Node next() {
            Node temp = next;
            next = next.next;
            return temp;
        }

        /**
         * This method will do nothing.
         */
        public void remove() {
            // do nothing.
        }

        public String toString() {
            return String.valueOf(data);
        }
    }
}

