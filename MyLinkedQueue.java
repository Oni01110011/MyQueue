import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Eine generische Warteschlange, die eine verknüpfte Liste verwendet, um Elemente zu speichern.
 * Diese Implementierung folgt dem FIFO-Prinzip (First-In-First-Out).
 * Durch Implementierung des Iterable-Interfaces kann die Klasse mit einer erweiterten for-Schleife
 * und anderen Iterationsmechanismen genutzt werden.
 *
 * @param <E> Der Typ der Elemente, die in der Warteschlange gespeichert werden.
 * @author Bernhard Aichinger-Ganas
 * @version 2024-03-26
 */
public class MyLinkedQueue<E> implements MyQueue<E>, Iterable<E> {
    private static class Cell<E> {
        E value;
        Cell<E> next;

        Cell(E element) {
            this.value = element;
            this.next = null;
        }
    }

    private Cell<E> front;
    private Cell<E> rear;
    private int count;

    public MyLinkedQueue() {
        front = rear = null;
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public MyQueue<E> append(E element) {
        Cell<E> newCell = new Cell<>(element);
        if (rear != null) {
            rear.next = newCell;
        }
        rear = newCell;
        if (front == null) {
            front = newCell;
        }
        count++;
        return this;
    }

    @Override
    public E delete() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E value = front.value;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        count--;
        return value;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.value;
    }

    @Override
    public E peekLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E element : this) {
            sb.append(element).append(" ");
        }
        return sb.toString().trim();
    }

    private class MyIterator implements Iterator<E> {
        private Cell<E> pointer = front;
        private Cell<E> previous = null;
        private boolean begin = true;
        private boolean removable = false;

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            if (!begin) {
                previous = (previous == null) ? front : previous.next;
            }
            begin = false;
            E value = pointer.value;
            pointer = pointer.next;
            removable = true;
            return value;
        }

        @Override
        public void remove() {
            if (!removable) {
                throw new IllegalStateException("Call next() before removing");
            }
            if (previous == null) {
                delete(); // remove from front
            } else {
                previous.next = pointer;
                count--;
            }
            removable = false;
        }
    }
}
    