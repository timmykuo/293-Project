package edu.cwru.tyk3.uxb;
import static org.junit.Assert.*;
import java.math.BigInteger;
import org.junit.Test;

public class BinaryMessageTest {

	@Test
	public void testConstructor() {
		BinaryMessage m1 = new BinaryMessage(null);
		BinaryMessage m2 = new BinaryMessage(BigInteger.valueOf(100));
		
		assertEquals("When creating a new binary message with null value, the value should be assigned with Big Integer zero value", m1.getValue(), BigInteger.ZERO);
		assertEquals("When creating a new binary message with a Big Integer, the value should have that Big Integer", m2.getValue(), BigInteger.valueOf(100));
	}
	
	@Test
	public void testEquals() {
		BinaryMessage m1 = new BinaryMessage(BigInteger.valueOf(82));
		BinaryMessage m2 = new BinaryMessage(BigInteger.valueOf(82));
		
		assertTrue("This should return true because underlying integers are equal", m1.equals(m2));
	}
}
