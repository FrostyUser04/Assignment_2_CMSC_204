public class StackOverflowException extends Exception {
    public StackOverflowException() {
        this("Stack overflow: no space to push");
    }
    public StackOverflowException(String message) {
        super(message);
    }
}