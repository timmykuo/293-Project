package edu.cwru.tyk3.uxb;

import java.math.BigInteger;
import java.util.ArrayList;

public class Broadcast {

	public static void main(String args[]) {
		//create a list of connector types
		ArrayList<Connector.Type> test = new ArrayList<Connector.Type>(); 
		test.add(Connector.Type.PERIPHERAL);
		test.add(Connector.Type.COMPUTER);
		
		//build a hub
		Hub.Builder hb = new Hub.Builder(Integer.valueOf(1));
		hb.productCode(Integer.valueOf(1));
		hb.serialNumber(BigInteger.valueOf(1));
		hb.connectors(test);
		Hub hub = hb.build();
		
		//printers and videos can only have connectors of type peripheral
		test.remove(1);
		//build a cannonprinter
		CannonPrinter.Builder cb = new CannonPrinter.Builder(Integer.valueOf(1));
		cb.productCode(Integer.valueOf(1));
		cb.serialNumber(BigInteger.valueOf(1));
		cb.connectors(test);
		CannonPrinter cannonprinter = cb.build();
		
		//build a sisterprinter
		SisterPrinter.Builder sb = new SisterPrinter.Builder(Integer.valueOf(1));
		sb.productCode(Integer.valueOf(1));
		sb.serialNumber(BigInteger.valueOf(1));
		sb.connectors(test);
		SisterPrinter sisterprinter = sb.build();
		
		//build a goamateur
		GoAmateur.Builder gb = new GoAmateur.Builder(Integer.valueOf(1));
		gb.productCode(Integer.valueOf(1));
		gb.serialNumber(BigInteger.valueOf(1));
		gb.connectors(test);
		GoAmateur goamateur = gb.build();
		
		//add the devices into a list
		ArrayList<Device> devices = new ArrayList<Device>();
		devices.add(hub);
		devices.add(cannonprinter);
		devices.add(sisterprinter);
		devices.add(goamateur);
		
		//add two messages into a list
		ArrayList<Message> messages = new ArrayList<Message>();
		messages.add(new StringMessage("I am a test"));
		messages.add(new BinaryMessage(BigInteger.valueOf(12345)));
		
		//test Connector.recv to Message.reach to Device.recv
		for(Device d : devices) {
			d.getConnector(0).recv(messages.get(0));
			d.getConnector(0).recv(messages.get(1));
		}
	}
}
