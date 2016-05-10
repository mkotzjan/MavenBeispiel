package de.mkotzjan;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * UnitTest for queue class
 */ 
public class QueueTest
{
    /**
     * Test normal behaviour of queue
     */
	@Test
    public void testQueueNormal()
    {
    	Queue q = new Queue(3);
    	q.enqueue(1);
    	q.enqueue(2);
    	q.enqueue(3);
    	assertEquals(q.dequeue(), 1);
    	assertEquals(q.dequeue(), 2);
    	assertEquals(q.dequeue(), 3);
    }
	
	/**
     * Test queue by adding more than needed items
     */
	@Test
    public void testQueueEnqueue()
    {
    	Queue q = new Queue(3);
    	q.enqueue(1);
    	q.enqueue(2);
    	q.enqueue(3);
    	q.enqueue(4);
    	q.enqueue(5);
    	assertEquals(q.dequeue(), 1);
    	assertEquals(q.dequeue(), 2);
    	assertEquals(q.dequeue(), 5);
    }
	
	/**
     * Test queue by adding more than needed items
     */
	@Test
    public void testQueueDequeue()
    {
    	Queue q = new Queue(3);
    	q.enqueue(1);
    	q.enqueue(2);
    	q.enqueue(3);
    	assertEquals(q.dequeue(), 1);
    	assertEquals(q.dequeue(), 2);
    	assertEquals(q.dequeue(), 3);
    	try
    	{
    		q.dequeue();
    		fail("Dequeue on empty queue should throw exception");
    	} catch (IllegalStateException e) {
    	}
    }
	
	/**
     * Test the circularity of the queue by adding and deleting entries
     */
	@Test
    public void testQueueCircular()
    {
    	Queue q = new Queue(3);
    	for (int i = 0; i < 15; ++i)
    	{
    		q.enqueue(i);
    		assertEquals("Value " + i + " was expected", q.dequeue(), i);
    	}
    }
}
