import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();

        int n = Integer.parseInt(args[0]);
        int k = 0;
        // StdOut.println(n);
        while(!StdIn.isEmpty()) {
            // String item = StdIn.readString();
            randQ.enqueue(StdIn.readString());
            k++;
        }

        while (k - n > 0) {
            randQ.dequeue();
            k--;
        }

        // StdOut.println("(" + randQ.size() + " left on random q)");
        for (int i = 0; i < n; i++) {
            StdOut.println(randQ.dequeue());
        }
    }
}
