import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();

        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();

            //if (!item.equals("-"))
                randQ.enqueue(item);
            // else if (!randQ.isEmpty())
            //     StdOut.print(randQ.dequeue() + " ");
        }
        // StdOut.println("(" + randQ.size() + " left on random q)");

        while(!randQ.isEmpty())
            StdOut.println(randQ.dequeue());
    }
}
