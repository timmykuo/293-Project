package edu.cwru.tyk3.uxb;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

public class HubTest {

	@Test
	public void testConstructorsAndBuild() {
		Hub.Builder hb = new Hub.Builder(Integer.valueOf(24));
		
		assertEquals("A new builder's version should be the same as the input parameter", hb.getVersion(), Integer.valueOf(24));
		assertEquals("A new builder's product code should be an empty optional", hb.getProductCode(), Optional.empty());
		assertEquals("A new builder's serial number should be an empty optional", hb.getProductCode(), Optional.empty());
		assertEquals("A new builder's connector list should be an empty list", hb.getConnectors(), new ArrayList<>());
		
		//create a list of only peripherals
		ArrayList<Connector.Type> test = new ArrayList<Connector.Type>(); 
		test.add(Connector.Type.PERIPHERAL);
		test.add(Connector.Type.COMPUTER);
		
		//initialize product code, serial number, connectors
		hb.productCode(Integer.valueOf(3));
		hb.serialNumber(BigInteger.valueOf(3));
		hb.connectors(test);
		Hub h = hb.build();
		
		assertEquals("A new hub's version should be the same as the input parameter", h.getVersion(), Integer.valueOf(24));
		assertEquals("A new hub's product code should be an empty optional", h.getProductCode(), Optional.of(Integer.valueOf(3)));
		assertEquals("A new hub's serial number should be an empty optional", h.getSerialNumber(), Optional.of(BigInteger.valueOf(3)));
		assertEquals("A new hub's connector list should be a list of connectors made from the connectorTypes list", h.getConnectors().size(), test.size());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testValidateNullVersion() {
		Hub.Builder hb = new Hub.Builder(null);
		hb.validate();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testValidateNullConnectors() {
		Hub.Builder hb = new Hub.Builder(Integer.valueOf(1));
		hb.connectors(null);
		hb.validate();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testValidateNoPeripherals() {
		Hub.Builder hb = new Hub.Builder(Integer.valueOf(2));
		//create a list of connector type computer
		ArrayList<Connector.Type> test = new ArrayList<Connector.Type>(); 
		for(int i = 0; i < 3; i++) {
			test.add(Connector.Type.COMPUTER);
		}
		
		hb.validate();
	}
}
