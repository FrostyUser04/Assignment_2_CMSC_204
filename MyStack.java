import java.util.ArrayList;

/**
 * @author Yesho Vir
 */

public class MyStack<T> implements StackInterface<T> {

    private int numOfElements;
    private final int MAX_SIZE;
    private ArrayList<T> elements;

    public MyStack() {
        this(5);
    }

    /**
     * 
     * @param size
     */

    public MyStack(int size) {
        MAX_SIZE = size;
        elements = new ArrayList<>(MAX_SIZE);
        numOfElements = 0;
    }

    /**
     * 
     * @param elements
     */

    public MyStack(ArrayList<T> elements) {
        this(elements.size());
        for (T element : elements) {
            try {
                push(element);
            } catch (StackOverflowException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return numOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return numOfElements == MAX_SIZE;
    }

    /**
     * 
     * @return
     * @throws StackUnderflowException
     */

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        }
        T element = elements.remove(--numOfElements);
        return element;
    }

    /**
     * 
     * @return
     * @throws StackUnderflowException
     */

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        }
        return elements.get(numOfElements - 1);
    }

    /**
     * 
     * @return
     */

    @Override
    public int size() {
        return numOfElements;
    }

    /**
     * 
     * @param e
     * @return
     * @throws StackOverflowException
     */

    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException();
        }
        elements.add(e);
        numOfElements++;
        return true;
    }

    @Override
    public String toString() {
        if (numOfElements == 0) return "";
        String result = elements.get(0).toString();
        for (int i = 1; i < numOfElements; i++) {
            if (elements.get(i) != null) {
                result += elements.get(i).toString();
            }
        }
        return result;
    }

    /**
     * 
     * @param delimiter
     * @return
     */

    @Override
    public String toString(String delimiter) {
        if (numOfElements == 0) return "";
        String result = elements.get(0).toString();
        for (int i = 1; i < numOfElements; i++) {
            if (elements.get(i) != null) {
                result += delimiter + elements.get(i).toString();
            }
        }
        return result;
    }

    /**
     * 
     * @param list
     */

    @Override
    public void fill(ArrayList<T> list) {
        for (T element : list) {
            try { 
                push(element);
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }
    }
}
