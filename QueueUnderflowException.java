public class QueueUnderflowException extends Exception {
    public QueueUnderflowException() {
        this("Queue underflow: no element to dequeue");
    }
    public QueueUnderflowException(String message) {
        super(message);
    }
}