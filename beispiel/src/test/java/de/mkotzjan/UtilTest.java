package de.mkotzjan;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for Util class
 */ 
public class UtilTest
{
    /**
     * Test Util.istErstesHalbjahr(int monat)
     */
	@Test
    public void testIstErstesHalbjahrIn()
    {
    	for (int i = 1; i < 6; ++i)
    	{
    		assertTrue("Month " + i + " should be inside.", Util.istErstesHalbjahr(i));
    	}
    }
    
	@Test
    public void testIstErstesHalbjahrOut()
    {
    	for (int i = 7; i < 13; ++i)
    	{
    		assertFalse("Month " + i + " should be outside", Util.istErstesHalbjahr(i));
    	}
    }
	
	@Test
    public void testIstErstesHalbjahrException()
    {
    	try
    	{
    		Util.istErstesHalbjahr(0);
    		fail("Exception should be thrown");
    	} catch (IllegalArgumentException e) {	
    	}
    	
    	try
    	{
    		Util.istErstesHalbjahr(13);
    		fail("Exception should be thrown");
    	} catch (IllegalArgumentException e) {
    	}
    }
}
