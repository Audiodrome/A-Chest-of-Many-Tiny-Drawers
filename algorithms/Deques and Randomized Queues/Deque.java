//package edu.princeton.cs.algs4;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int n;

    private class Node {
        private Item item;
        private Node next;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return n; }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (isEmpty())
            last = first;
        else
            first.next = oldfirst;
        n++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        n--;
        return item;
    }

    public Item removeLast() {
        Item item = last.item;
        last = last.next;
        if (isEmpty())
            first = null;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> deck = new Deque<String>();

    //     while (!StdIn.isEmpty()) {
    //         String item = StdIn.readString();
    //         if (!item.equals("-"))
    //             deck.addLast(item);
    //         else if (!deck.isEmpty())
    //             StdOut.print(deck.removeFirst() + " ");
    //     }
    //     StdOut.println(deck.size() + " left on queue");
    //
    }

}
