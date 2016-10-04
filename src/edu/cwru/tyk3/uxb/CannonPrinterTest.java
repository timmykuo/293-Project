package edu.cwru.tyk3.uxb;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

public class CannonPrinterTest {

	@Test
	public void testConstructorsAndBuild() {
		CannonPrinter.Builder cb = new CannonPrinter.Builder(Integer.valueOf(24));
		
		assertEquals("A new builder's version should be the same ac the input parameter", cb.getVersion(), Integer.valueOf(24));
		assertEquals("A new builder's product code should be an empty optional", cb.getProductCode(), Optional.empty());
		assertEquals("A new builder's serial number should be an empty optional", cb.getProductCode(), Optional.empty());
		assertEquals("A new builder's connector list should be an empty list", cb.getConnectors(), new ArrayList<>());
		
		//create a list of only peripherals
		ArrayList<Connector.Type> test = new ArrayList<Connector.Type>(); 
		test.add(Connector.Type.PERIPHERAL);
		test.add(Connector.Type.COMPUTER);
		
		//initialize product code, serial number, connectors
		cb.productCode(Integer.valueOf(3));
		cb.serialNumber(BigInteger.valueOf(3));
		cb.connectors(test);
		CannonPrinter c = cb.build();
		
		assertEquals("A new CannonPrinter's version should be the same ac the input parameter", c.getVersion(), Integer.valueOf(24));
		assertEquals("A new CannonPrinter's product code should be an empty optional", c.getProductCode(), Optional.of(Integer.valueOf(3)));
		assertEquals("A new CannonPrinter's serial number should be an empty optional", c.getSerialNumber(), Optional.of(BigInteger.valueOf(3)));
		assertEquals("A new CannonPrinter's connector list should be a list of connectors made from the connectorTypes list", c.getConnectors().size(), test.size());
	}

}

