import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;

    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (n == q.length)
            resize(2*q.length);
        q[n++] = item;
        // StdRandom.shuffle(q, 0, n-1);
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int i = StdRandom.uniform(0, n);

        Item tmp = q[i];
        q[i] = q[n-1];
        //q[n-1] = tmp;

        Item item = tmp;
        q[n-1] = null;
        n--;

        if(n > 0 && n == q.length/4)
            resize(q.length/2);
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return q[n-1];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i;

        public ArrayIterator() {
            i = n - 1;
        }
        public boolean hasNext() {
            return i >= 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return q[i--];
        }
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[i]; 
        }
        q = temp;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();

        randQ.enqueue("a");
        randQ.enqueue("b");
        randQ.enqueue("c");
        randQ.enqueue("d");
        randQ.enqueue("e");

        System.out.println("-------------");

        for (String item : randQ)
            System.out.println(item);

        randQ.enqueue("d");
        randQ.enqueue("e");
        System.out.println("-------------");
        for (String item : randQ)
            System.out.println(item);


        // while (!randQ.isEmpty()) {
        //     System.out.println(randQ.dequeue());
        // }
    }
}
