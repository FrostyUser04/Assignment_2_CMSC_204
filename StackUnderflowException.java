public class StackUnderflowException extends Exception {
    public StackUnderflowException() {
        super("Stack underflow: no element to pop");
    }
    public StackUnderflowException(String message) {
        super(message); 
    }
}