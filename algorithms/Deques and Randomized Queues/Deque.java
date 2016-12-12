import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head, tail;
    private int n;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        head = null;
        tail = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }

    public void addHead(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldhead = head;
        head = new Node();
        head.item = item;
        head.prev = null;
        if (isEmpty()) {
            head.next = null;
            tail = head;
        }
        else {
            head.next = oldhead;
            oldhead.prev = head;
        }
        n++;
    }

    public void addTail(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldtail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) {
            tail.prev = null;
            head = tail;
        }
        else {
            oldtail.next = tail;
            tail.prev = oldtail;
        }
        n++;
    }

    public Item removeHead() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = head.item;

        if (n == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        n--;
        return item;
    }

    public Item removeTail() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item = tail.item;

        if (n == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // public static void main(String[] args) {
    //     Deque<String> deck = new Deque<String>();

    //     deck.addTail("a");
    //     deck.addTail("b");
    //     deck.addHead("c");
    //     deck.addHead("d");
    //     deck.addTail("e");

    //     deck.removeHead();
    //     deck.removeHead();
    //     deck.removeHead();
    //     deck.removeHead();
    //     deck.removeHead();
    //     deck.removeHead();

    //     deck.removeTail();
    //     deck.removeTail();
    //     deck.removeTail();
    //     deck.removeTail();


    //     for (String item : deck)
    //         System.out.println(item);
    //     System.out.println("-------------");
    //     while (!deck.isEmpty()) {
    //         System.out.print(deck.removeHead());
    //     }
    //     System.out.println("Size: " + deck.size());

    // }

}
