import java.util.ArrayList;

/**
 * @author Yesho Vir
 */

public class MyQueue<T> implements QueueInterface<T> {

    private final int MAX_SIZE;
    private ArrayList<T> elements;

    public MyQueue() {
        this(5);
    }

    /**
     * 
     * @param size
     */

    public MyQueue(int size) {
        MAX_SIZE = size;
        elements = new ArrayList<>(MAX_SIZE);
    }

    /**
     * 
     * @param elements
     */

    public MyQueue(ArrayList<T> elements) {
        this(elements.size());
        for (T element : elements) {
            try {
                enqueue(element);
            } catch (QueueOverflowException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean isFull() {
        return elements.size() == MAX_SIZE;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        return elements.remove(0); // Remove and return the element at the front of the queue
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }
        elements.add(e); // Add element at the end of the queue
        return true;
    }

    @Override
    public String toString() {
        if (elements.isEmpty()) return "";
        String result = elements.get(0).toString();
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i) != null) {
                result += elements.get(i).toString();
            }
        }
        return result;
    }

    @Override
    public String toString(String delimiter) {
        if (elements.isEmpty()) return "";
        String result = elements.get(0).toString();
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i) != null) {
                result += delimiter + elements.get(i).toString();
            }
        }
        return result;
    }

    @Override
    public void fill(ArrayList<T> list) {
        for (T element : list) {
            try {
                enqueue(element);
            } catch (QueueOverflowException e) {
                e.printStackTrace();
            }
        }
    }
}
