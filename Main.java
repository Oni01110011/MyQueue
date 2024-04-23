import java.util.Iterator;
import java.util.NoSuchElementException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyLinkedQueue<Integer> queue = new MyLinkedQueue<>();

        // Test appending elements
        queue.append(1).append(2).append(3);

        // Test isEmpty
        System.out.println("Is the queue empty? " + queue.isEmpty());

        // Test size
        System.out.println("Size of queue: " + queue.size());

        // Test peek
        try {
            System.out.println("First element: " + queue.peek());
            System.out.println("Last element: " + queue.peekLast());
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty.");
        }

        // Test iterator and toString
        System.out.println("Queue content: " + queue);

        // Test removing elements
        try {
            System.out.println("Removed element: " + queue.delete());
            System.out.println("Queue content after delete: " + queue);
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty.");
        }

        // Test the iterator's remove method
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value.equals(2)) {
                iterator.remove();
            }
        }

        System.out.println("Queue content after iterator remove: " + queue);
    }
}