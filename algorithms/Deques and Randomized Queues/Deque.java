
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int N;

    private class Node
    {
        Item item;
        Node next;
    }

    public Deque() {
        
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }

    public static void main(String[] args) {

        Deque deck = new Deque();
    }

    private 
}
