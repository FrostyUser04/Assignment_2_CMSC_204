public class QueueOverflowException extends Exception {
    public QueueOverflowException() {
        this("Queue overflow: no space to enqueue");
    }

    public QueueOverflowException(String message) {
        super(message);
    }
}