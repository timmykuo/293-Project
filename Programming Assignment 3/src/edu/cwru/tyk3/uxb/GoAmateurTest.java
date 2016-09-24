package edu.cwru.tyk3.uxb;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

public class GoAmateurTest {

	@Test
	public void testConstructorsAndBuild() {
		GoAmateur.Builder gb = new GoAmateur.Builder(Integer.valueOf(24));
		
		assertEquals("A new builder's version should be the same as the input parameter", gb.getVersion(), Integer.valueOf(24));
		assertEquals("A new builder's product code should be an empty optional", gb.getProductCode(), Optional.empty());
		assertEquals("A new builder's serial number should be an empty optional", gb.getProductCode(), Optional.empty());
		assertEquals("A new builder's connector list should be an empty list", gb.getConnectors(), new ArrayList<>());
		
		//create a list of only peripherals
		ArrayList<Connector.Type> test = new ArrayList<Connector.Type>(); 
		test.add(Connector.Type.PERIPHERAL);
		test.add(Connector.Type.COMPUTER);
		
		//initialize product code, serial number, connectors
		gb.productCode(Integer.valueOf(3));
		gb.serialNumber(BigInteger.valueOf(3));
		gb.connectors(test);
		GoAmateur g = gb.build();
		
		assertEquals("A new GoAmateur's version should be the same as the input parameter", g.getVersion(), Integer.valueOf(24));
		assertEquals("A new GoAmateur's product code should be an empty optional", g.getProductCode(), Optional.of(Integer.valueOf(3)));
		assertEquals("A new GoAmateur's serial number should be an empty optional", g.getSerialNumber(), Optional.of(BigInteger.valueOf(3)));
		assertEquals("A new GoAmateur's connector list should be a list of connectors made from the connectorTypes list", g.getConnectors().size(), test.size());
	}

}

