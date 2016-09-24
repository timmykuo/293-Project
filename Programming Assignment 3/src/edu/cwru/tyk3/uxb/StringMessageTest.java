package edu.cwru.tyk3.uxb;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringMessageTest {
	
		@Test
		public void testConstructor() {
			StringMessage m1 = new StringMessage(null);
			StringMessage m2 = new StringMessage("This is a test");
			
			assertEquals("When creating a new string message with null, the underying string should be null", m1.getString(), "");
			assertEquals("When creating a new string message with a string, the underylying string should be that string", m2.getString(), "This is a test");
		}
		
		@Test
		public void testEquals() {
			StringMessage m1 = new StringMessage("This is a test");
			StringMessage m2 = new StringMessage("This is a test");
			
			assertTrue("This should return true because the underlying strings are equal", m1.equals(m2));
		}
}
