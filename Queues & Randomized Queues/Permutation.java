import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {

        int argument = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < argument; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}