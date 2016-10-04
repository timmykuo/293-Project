package edu.cwru.tyk3.uxb;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class ConnectorTest {
	
	private ArrayList<Connector.Type> test; 
	private Hub hub;
	private CannonPrinter cannonprinter;
	private SisterPrinter sisterprinter;
	private Connector connector1;
	private Connector connector2;
	private Connector connector3;
	private Connector connector4;
	
	@Before
	public void initialize() {
		//create a list of connector types
		test = new ArrayList<Connector.Type>(); 
		test.add(Connector.Type.PERIPHERAL);
		test.add(Connector.Type.COMPUTER);
		
		//build a hub
		Hub.Builder hb = new Hub.Builder(Integer.valueOf(1));
		hb.productCode(Integer.valueOf(1));
		hb.serialNumber(BigInteger.valueOf(1));
		hb.connectors(test);
		hub = hb.build();
		
		//printers and videos can only have connectors of type peripheral
		test.remove(1);
		test.add(Connector.Type.PERIPHERAL);
		//build a cannonprinter
		CannonPrinter.Builder cb = new CannonPrinter.Builder(Integer.valueOf(1));
		cb.productCode(Integer.valueOf(1));
		cb.serialNumber(BigInteger.valueOf(1));
		cb.connectors(test);
		cannonprinter = cb.build();
		
		//build a sisterprinter
		SisterPrinter.Builder sb = new SisterPrinter.Builder(Integer.valueOf(1));
		sb.productCode(Integer.valueOf(1));
		sb.serialNumber(BigInteger.valueOf(1));
		sb.connectors(test);
		sisterprinter = sb.build();
		
		connector1 = new Connector(hub, 0, Connector.Type.PERIPHERAL);
		connector2 = new Connector(cannonprinter, 0, Connector.Type.PERIPHERAL);
		connector3 = new Connector(sisterprinter, 0, Connector.Type.PERIPHERAL);
		connector4 = new Connector(sisterprinter, 0, Connector.Type.PERIPHERAL);
		
	}

	@Test
	public void testConstructor() {
		assertEquals("The device of the new connector should be the given device", hub, connector1.getDevice());
		assertEquals("The index of the new connector should be the given index", 0, connector1.getIndex());
		assertEquals("The type of the new connector should be the given type", Connector.Type.PERIPHERAL, connector1.getType());
		assertEquals("The peer of the new connector should be an empty optional", Optional.empty(), connector1.getPeer());
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetPeer1() throws ConnectionException{
		connector1.setPeer(null);
	}
	
	//throws connector busy exception
	@Test(expected=ConnectionException.class)
	public void testSetPeer2() throws ConnectionException{
		connector2.setPeer(connector3);
		connector2.setPeer(connector4);
	}
	
	//throws connection mismatch exception
	@Test(expected=ConnectionException.class)
	public void testSetPeer3() throws ConnectionException{
		connector1.setPeer(connector2);
	}
	
	//throws connection cycle exception
	@Test(expected=ConnectionException.class)
	public void testSetPeer4() throws ConnectionException{
		connector1.setPeer(connector1);
	}
}
