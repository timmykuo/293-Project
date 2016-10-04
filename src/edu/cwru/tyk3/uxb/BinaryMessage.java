package edu.cwru.tyk3.uxb;
import java.math.BigInteger;

public class BinaryMessage implements Message {

	private final BigInteger value;
	
	public BinaryMessage(BigInteger value) {
		//if value entered is null, the message should contain a zero
		if(value == null) {
			this.value = BigInteger.ZERO;
		}
		else {
			this.value = new BigInteger(value.toByteArray());
		}
	}
	
	public BigInteger getValue() {
		return this.value;
	}
	
	@Override
	public void reach(Device device, Connector connector) {
		device.recv(this, connector);
	}
	
	//returns true if the object is not null, is a BinaryMessage object, and the underlying integers are equal
	public boolean equals(Object anObject) {
		//using short circuit evaluation, so simply typecast after checking isbinarymessage()
		return (!isNull(anObject) &&
				 isBinaryMessage(anObject) && 
				 isValuesEqual((BinaryMessage)anObject, this));
	}
	
	//checks if the object is null
	private boolean isNull(Object anObject) {
		return anObject == null;
	}
	
	//checks if the object is a binary message
	private boolean isBinaryMessage(Object anObject) {
		return anObject instanceof BinaryMessage;
	}
	
	//checks if the two binary messages underlying integers are equal
	private boolean isValuesEqual(BinaryMessage tocmp, BinaryMessage original) {
		return tocmp.getValue().equals(original.getValue());
	}
}
