package DSA;

public class Queue {
    private Node head, tail;
    private int count = 0;

    public Queue() {
        super();
    }

    private void initiate(int n) {
        count = 1;
        head = new Node(n);
        tail = head;
    }

    public void add(int n) {
        if (count == 0) {
            initiate(n);
            return;
        }

        count++;
        tail.next = new Node(n);
        tail = tail.next;
    }

    public void remove() {
        if (count > 0) {
            count--;
            head = head.next;
        }
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "\t");
            temp = temp.next;
        }
        System.out.println();
    }

    public int get() {
        return head.data;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int count() {
        return count;
    }

    class Node {
        int data;
        Node next;

        Node(int n) {
            this.data = n;
        }

        Node(int n, Node next) {
            this.data = n;
            this.next = next;
        }
    }
}
