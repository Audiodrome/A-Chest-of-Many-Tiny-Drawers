import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int n;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (isEmpty()) {
            first.next = null;
            last = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            last.prev = null;
            first = last;
        }
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;

        if (n == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        n--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item = last.item;

        if (n == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

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

    //     deck.addLast("a");
    //     deck.addLast("b");
    //     deck.addFirst("c");
    //     deck.addFirst("d");
    //     deck.addLast("e");

    //     deck.removeFirst();
    //     deck.removeFirst();
    //     deck.removeFirst();
    //     deck.removeFirst();
    //     deck.removeFirst();
    //     deck.removeFirst();

    //     deck.removeLast();
    //     deck.removeLast();
    //     deck.removeLast();
    //     deck.removeLast();


    //     for (String item : deck)
    //         System.out.println(item);
    //     System.out.println("-------------");
    //     while (!deck.isEmpty()) {
    //         System.out.print(deck.removeFirst());
    //     }
    //     System.out.println("Size: " + deck.size());

    // }

}
