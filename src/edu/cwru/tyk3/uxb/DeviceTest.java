package edu.cwru.tyk3.uxb;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DeviceTest {
	
	Hub hub1;
	Hub hub2;
	CannonPrinter cannonprinter;
	SisterPrinter sisterprinter;
	SisterPrinter sisterprinter2;
	GoAmateur goamateur;

	@Before
	public void initialize() throws ConnectionException{
		//create a list of connector types
		ArrayList<Connector.Type> test = new ArrayList<Connector.Type>(); 
		test.add(Connector.Type.COMPUTER);
		test.add(Connector.Type.PERIPHERAL);
		test.add(Connector.Type.COMPUTER);
		test.add(Connector.Type.COMPUTER);
		
		//build a hub
		Hub.Builder hb = new Hub.Builder(Integer.valueOf(1));
		hb.productCode(Integer.valueOf(1));
		hb.serialNumber(BigInteger.valueOf(1));
		hb.connectors(test);
		hub1 = hb.build();
		hub2 = hb.build();
		
		//printers and videos can only have connectors of type peripheral
		ArrayList<Connector.Type> test2 = new ArrayList<Connector.Type>(); 
		test2.add(Connector.Type.PERIPHERAL);
		//build a cannonprinter
		CannonPrinter.Builder cb = new CannonPrinter.Builder(Integer.valueOf(1));
		cb.productCode(Integer.valueOf(1));
		cb.serialNumber(BigInteger.valueOf(1));
		cb.connectors(test2);
		cannonprinter = cb.build();
		
		//build a sisterprinter
		SisterPrinter.Builder sb = new SisterPrinter.Builder(Integer.valueOf(1));
		sb.productCode(Integer.valueOf(1));
		sb.serialNumber(BigInteger.valueOf(1));
		sb.connectors(test2);
		sisterprinter = sb.build();
		
		SisterPrinter.Builder sb2 = new SisterPrinter.Builder(Integer.valueOf(1));
		sb2.productCode(Integer.valueOf(1));
		sb2.serialNumber(BigInteger.valueOf(2));
		sb2.connectors(test2);
		sisterprinter2 = sb2.build();
		
		//build a goamateur
		GoAmateur.Builder gb = new GoAmateur.Builder(Integer.valueOf(1));
		gb.productCode(Integer.valueOf(1));
		gb.serialNumber(BigInteger.valueOf(1));
		gb.connectors(test2);
		goamateur = gb.build();
		
		//setup connectors for hub1
		hub1.getConnector(0).setPeer(cannonprinter.getConnector(0));
		hub1.getConnector(1).setPeer(hub2.getConnector(3));
		hub1.getConnector(2).setPeer(sisterprinter.getConnector(0));
		
		//setup connectors for hub2
		hub2.getConnector(0).setPeer(sisterprinter2.getConnector(0));
		hub2.getConnector(2).setPeer(goamateur.getConnector(0));
	}
	
	@Test
	public void testReachableDevices() {
		
		System.out.println(hub2.reachableDevices());
	}
	
	@Test
	public void testBroadcast() {
		//add the devices into a list
		ArrayList<Device> devices = new ArrayList<Device>();
		devices.add(hub1);
		devices.add(cannonprinter);
		devices.add(sisterprinter);
		devices.add(goamateur);
		
		//add two messages into a list
		ArrayList<Message> messages = new ArrayList<Message>();
		messages.add(new StringMessage("I am a test"));
		messages.add(new BinaryMessage(BigInteger.valueOf(12345)));
		
		//test Connector.recv to Message.reach to Device.recv
		for(Device d : devices) {
			for(Message m : messages) {
				d.getConnector(0).recv(m);
			}
		}
		System.out.println("\n");
	}
	
	@Test
	public void testBinaryMessageToWebcam() {
		BinaryMessage b = new BinaryMessage(BigInteger.valueOf(1));
		hub1.getConnector(1).recv(b);
		System.out.println("\n");
	}
	
	@Test
	public void testStringMessageFromHub(){
		StringMessage m = new StringMessage("testStringMessageFromHub");
		hub1.getConnector(0).recv(m);
		System.out.println("\n");
	}
	
	@Test
	public void testBinaryMessageFromHub() {
		BinaryMessage b = new BinaryMessage(BigInteger.valueOf(2));
		hub1.getConnector(0).recv(b);
	}
}
