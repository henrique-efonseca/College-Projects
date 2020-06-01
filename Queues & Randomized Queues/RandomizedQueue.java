
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randomQueue;
    private int size;

// construct an empty randomized queue
    public RandomizedQueue() {
        this.randomQueue = (Item[]) new Object[8];
        this.size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int newSize) {
        Item[] copyRandomizedQueue = (Item[]) new Object[newSize];

        for (int i = 0; i < size; i++) {
            copyRandomizedQueue[i] = randomQueue[i];
        }
        randomQueue = copyRandomizedQueue;

    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == randomQueue.length) {
            resize(size * 2);
        }
        randomQueue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size > 0 && size == randomQueue.length / 4) {
            resize(randomQueue.length / 2);
        }

        int randomIndex = StdRandom.uniform(size);
        Item item = randomQueue[randomIndex];
        randomQueue[randomIndex] = randomQueue[size - 1];
        randomQueue[size - 1] = null;
        size--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return randomQueue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int copySize;
        private int[] randomIndices;

        public ArrayIterator() {
            copySize = 0;
            randomIndices = new int[size];
            for (int j = 0; j < size; j++) {
                randomIndices[j] = j;
            }
            StdRandom.shuffle(randomIndices);
        }

        @Override
        public boolean hasNext() {
            return copySize < size;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return randomQueue[randomIndices[copySize++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // testing (required)
    public static void main(String[] args) {
        System.out.println("Running remove/resize test.");

        // System.out.println("Make sure to uncomment the lines below (and delete this
        // print statement).");
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        // should be empty
        System.out.println("isEmpty() returned " + rq.isEmpty());

        for (int i = 0; i < 100; i++) {
            rq.enqueue(i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(rq.dequeue());
        }

        // should not be empty
        System.out.println("isEmpty() returned " + rq.isEmpty());

    }

}
