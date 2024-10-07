import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyStackTest {
    public MyStack<String> stringS;
    public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    public ArrayList<String> fill = new ArrayList<String>();
    public MyStack<Double> doubleS;

    @Before
    public void setUp() throws Exception {
        stringS = new MyStack<String>(5);
        stringS.push(a);
        stringS.push(b);
        stringS.push(c);

        doubleS = new MyStack<Double>();
        doubleS.push(3.4);
        doubleS.push(4.5);
        doubleS.push(44.3);
        doubleS.push(8.4);
    }

    @After
    public void tearDown() throws Exception {
        stringS = null;
        doubleS = null;
    }

    @Test
    public void testIsEmpty() throws StackUnderflowException {
        assertEquals(false, stringS.isEmpty());
        stringS.pop();
        stringS.pop();
        stringS.pop();
        assertEquals(true, stringS.isEmpty());
    }

    @Test
    public void testIsFull() throws StackOverflowException {
        assertEquals(false, stringS.isFull());
        stringS.push(d);
        stringS.push(e);
        assertEquals(true, stringS.isFull());
    }

    @Test
    public void testPop() {
        try {
            assertEquals(c, stringS.pop());
            assertEquals(b, stringS.pop());
            assertEquals(a, stringS.pop());
            stringS.pop();
            assertTrue(false);
        } catch (StackUnderflowException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testPopStudent() throws StackUnderflowException {
        doubleS.pop();
        doubleS.pop();
        assertEquals("3.44.5", doubleS.toString());
        doubleS.pop();
        assertEquals("3.4", doubleS.toString());
        doubleS.pop();
        assertEquals("", doubleS.toString());
        try {
            doubleS.pop();
            assertTrue(false);
        } catch (StackUnderflowException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testTop() throws StackOverflowException, StackUnderflowException {
        assertEquals(c, stringS.top());
        stringS.push(d);
        assertEquals(d, stringS.top());
        stringS.pop();
        stringS.pop();
        assertEquals(b, stringS.top());
    }

    @Test
    public void testSize() throws StackUnderflowException, StackOverflowException {
        assertEquals(3, stringS.size());
        stringS.push(d);
        assertEquals(4, stringS.size());
        stringS.pop();
        stringS.pop();
        assertEquals(2, stringS.size());
    }

    @Test
    public void testPush() {
        try {
            assertEquals(3, stringS.size());
            assertEquals(true, stringS.push(d));
            assertEquals(4, stringS.size());
            assertEquals(true, stringS.push(e));
            assertEquals(5, stringS.size());
            stringS.push(f);
            assertTrue(false);
        } catch (StackOverflowException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testPushStudent() throws StackOverflowException {
        doubleS.push(5.4);
        assertEquals("3.44.544.38.45.4", doubleS.toString());

        try {
            doubleS.push(1.0);
            assertTrue(false);
        } catch (StackOverflowException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testToString() throws StackOverflowException {
        assertEquals("abc", stringS.toString());
        stringS.push(d);
        assertEquals("abcd", stringS.toString());
        stringS.push(e);
        assertEquals("abcde", stringS.toString());
    }

    @Test
    public void testToStringStudent() throws StackUnderflowException, StackOverflowException {
        assertEquals("3.4, 4.5, 44.3, 8.4", doubleS.toString(", "));
        doubleS.pop();
        assertEquals("3.4, 4.5, 44.3", doubleS.toString(", "));
        doubleS.push(5.88);
        assertEquals("3.4, 4.5, 44.3, 5.88", doubleS.toString(", "));
        doubleS.push(64.33);
        assertEquals("3.4, 4.5, 44.3, 5.88, 64.33", doubleS.toString(", "));
    }

    @Test
    public void testToStringDelimiter() throws StackOverflowException {
        assertEquals("a%b%c", stringS.toString("%"));
        stringS.push(d);
        assertEquals("a&b&c&d", stringS.toString("&"));
        stringS.push(e);
        assertEquals("a/b/c/d/e", stringS.toString("/"));
    }

    @Test
    public void testFill() throws StackUnderflowException {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        stringS = new MyStack<String>(fill);
        assertEquals(3, stringS.size());
        assertEquals("carrot", stringS.pop());
        assertEquals("banana", stringS.pop());
        assertEquals("apple", stringS.pop());
        assertTrue(stringS.isEmpty());
    }
}
