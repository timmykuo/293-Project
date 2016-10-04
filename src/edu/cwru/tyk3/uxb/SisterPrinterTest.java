package edu.cwru.tyk3.uxb;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

public class SisterPrinterTest {

	@Test
	public void testConstructorsAndBuild() {
		SisterPrinter.Builder sb = new SisterPrinter.Builder(Integer.valueOf(24));
		
		assertEquals("A new builder's version should be the same as the input parameter", sb.getVersion(), Integer.valueOf(24));
		assertEquals("A new builder's product code should be an empty optional", sb.getProductCode(), Optional.empty());
		assertEquals("A new builder's serial number should be an empty optional", sb.getProductCode(), Optional.empty());
		assertEquals("A new builder's connector list should be an empty list", sb.getConnectors(), new ArrayList<>());
		
		//create a list of only peripherals
		ArrayList<Connector.Type> test = new ArrayList<Connector.Type>(); 
		test.add(Connector.Type.PERIPHERAL);
		test.add(Connector.Type.COMPUTER);
		
		//initialize product code, serial number, connectors
		sb.productCode(Integer.valueOf(3));
		sb.serialNumber(BigInteger.valueOf(3));
		sb.connectors(test);
		SisterPrinter s = sb.build();
		
		assertEquals("A new SisterPrinter's version should be the same as the input parameter", s.getVersion(), Integer.valueOf(24));
		assertEquals("A new SisterPrinter's product code should be an empty optional", s.getProductCode(), Optional.of(Integer.valueOf(3)));
		assertEquals("A new SisterPrinter's serial number should be an empty optional", s.getSerialNumber(), Optional.of(BigInteger.valueOf(3)));
		assertEquals("A new SisterPrinter's connector list should be a list of connectors made from the connectorTypes list", s.getConnectors().size(), test.size());
	}

}
