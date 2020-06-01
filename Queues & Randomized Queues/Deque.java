import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by:
 * 
 * Bernardo Bento nº 92877 Henrique Fonseca nº 94089
 * 
 */

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The item can not be null.");
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = oldFirst;

        if (oldFirst != null) {
            oldFirst.previous = first;
        } else {
            last = first;
        }

        size++;

    }

    // add the item to the back (enqueue)
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("The item can not be null.");
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;

        if (oldLast != null) {
            oldLast.next = last;
        } else {
            first = last;
        }

        size++;

    }

    // remove and return the item from the front (dequeue)
    public Item removeFirst() {

        if (isEmpty()) {

            throw new NoSuchElementException("Deque is empty. Can not remove item.");
        }

        Item item = first.item;
        first = first.next;

        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }

        size--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty. Can not remove item.");
        }

        Item item = last.item;
        last = last.previous;

        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }

        size--;

        return item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Deque does not have a next item.");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation not supported.");
        }
    }

    // testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(4);
        deque.removeLast();
        System.out.println(deque.isEmpty());
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 69);
        }
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
        deque.addFirst(5);
        System.out.println(deque.removeFirst());
        deque.addFirst(9);
        System.out.println(deque.removeFirst());
       
    }

}