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
        int i = StdRandom.uniform(0, n);
        return q[i];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private int k = n;
        private Item arr[] = (Item[]) new Object[k];

        public ArrayIterator() {
            for (int i = 0; i < k; i++)
                arr[i] = q[i];
        }

        public boolean hasNext() {
            return i <= k - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (arr[i] == null)
                throw new NoSuchElementException();

            int rand = StdRandom.uniform(0, k);
            Item item = arr[rand];
            if (rand != k - 1)
                arr[rand] = arr[k-1];
            k--;
            arr[k] = null;
            return item;
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

    // public static void main(String[] args) {
    //     RandomizedQueue<String> randQ = new RandomizedQueue<String>();

    //     randQ.enqueue("a");
    //     randQ.enqueue("b");
    //     randQ.enqueue("c");
    //     randQ.enqueue("d");
    //     randQ.enqueue("e");

    //     System.out.println("-------------");

    //     for (String item : randQ)
    //         System.out.println(item);

    //     randQ.enqueue("d");
    //     randQ.enqueue("e");
    //     System.out.println("-------------");
    //     for (String item : randQ)
    //         System.out.println(item);


    //     while (!randQ.isEmpty()) {
    //         System.out.println(randQ.dequeue());
    //     }
    // }
}
