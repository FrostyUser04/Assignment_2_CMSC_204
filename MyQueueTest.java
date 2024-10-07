import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {
	public MyQueue<String> stringQ;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	public MyQueue<Double> doubleQ;

	/**
	 * 
	 * @throws Exception
	 */

	@Before
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		
		doubleQ = new MyQueue<>(5);
		doubleQ.enqueue(4.6);
		doubleQ.enqueue(88.4);
		doubleQ.enqueue(55.3);
		doubleQ.enqueue(99.4);
	}

	/**
	 * 
	 * @throws Exception
	 */

	@After
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	/**
	 * 
	 * @throws QueueUnderflowException
	 */

	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		assertEquals(false, stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}

	/**
	 * 
	 * @throws QueueUnderflowException
	 */
	
	@Test
	public void testDequeueStudent() throws QueueUnderflowException {
		assertEquals("4.6, 88.4, 55.3, 99.4", doubleQ.toString(", "));
		assertEquals(doubleQ.dequeue(), 4.6, .0001);
		assertEquals(doubleQ.dequeue(), 88.4, .0001);
		assertEquals("55.3, 99.4", doubleQ.toString(", "));
		assertEquals(doubleQ.dequeue(), 55.3, .001);
		assertEquals("99.4", doubleQ.toString(", "));
		assertEquals(doubleQ.dequeue(), 99.4, .001);
		assertEquals("", doubleQ.toString(", "));
		
		try {
			doubleQ.dequeue();
			assertTrue("This should have caused a QueueUnderflowException", false);
		} catch (QueueUnderflowException e) {
			assertTrue("This caused a QueueUnderflowException, as expected!", true);
		} catch (Exception e) {
			assertTrue("This should have caused a QueueUnderflowException", false);
		}
	}

	/**
	 * 
	 * @throws QueueUnderflowException
	 * @throws QueueOverflowException
	 */

	@Test
	public void testSize() throws QueueUnderflowException, QueueOverflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			stringQ.enqueue(f);
			assertTrue("This should have caused an QueueOverflowException", false);
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	/**
	 * 
	 * @throws QueueOverflowException
	 */

	@Test
	public void testEnqueueStudent() throws QueueOverflowException {
		assertEquals("4.6, 88.4, 55.3, 99.4", doubleQ.toString(", "));
		assertTrue(doubleQ.enqueue(55.4));
		assertEquals("4.6, 88.4, 55.3, 99.4, 55.4", doubleQ.toString(", "));
		
		try {
			doubleQ.enqueue(4.0);
			assertTrue("This should have caused a QueueOverflowException", false);
		} catch (QueueOverflowException e) {
			assertTrue("This caused a QueueOverflowException, as expected!", true);
		} catch (Exception e) {
			assertTrue("This should have caused a QueueOverflowException", false);
		}
	}

	/**
	 * 
	 * @throws QueueOverflowException
	 */

	@Test
	public void testIsFull() throws QueueOverflowException {
		assertEquals(false, stringQ.isFull());
		stringQ.enqueue(d);
		stringQ.enqueue(e);
		assertEquals(true, stringQ.isFull());
	}

	/**
	 * 
	 * @throws QueueOverflowException
	 */

	@Test
	public void testToString() throws QueueOverflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
	}

	/**
	 * 
	 * @throws QueueOverflowException
	 * @throws QueueUnderflowException
	 */
	
	@Test
	public void testToStringStudent() throws QueueOverflowException, QueueUnderflowException {
		assertEquals("4.6, 88.4, 55.3, 99.4", doubleQ.toString(", "));
		doubleQ.enqueue(83.5);
		assertEquals("4.6, 88.4, 55.3, 99.4, 83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		assertEquals("88.4, 55.3, 99.4, 83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		doubleQ.dequeue();
		assertEquals("99.4, 83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		assertEquals("83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		assertEquals("", doubleQ.toString());
	}

	/**
	 * 
	 * @throws QueueOverflowException
	 */

	@Test
	public void testToStringDelimiter() throws QueueOverflowException {
		assertEquals("a%b%c", stringQ.toString("%"));
		stringQ.enqueue(d);
		assertEquals("a&b&c&d", stringQ.toString("&"));
		stringQ.enqueue(e);
		assertEquals("a/b/c/d/e", stringQ.toString("/"));
	}

	/**
	 * 
	 * @throws QueueUnderflowException
	 */

	@Test
	public void testFill() throws QueueUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		stringQ = new MyQueue<String>(fill);
		assertEquals(3,stringQ.size());
		assertEquals("apple", stringQ.dequeue());
		assertEquals("banana", stringQ.dequeue());
		assertEquals("carrot", stringQ.dequeue());		
	}
}
