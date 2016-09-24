package edu.cwru.tyk3.uxb;
import java.math.BigInteger;

public class BinaryMessage implements Message {

	private final BigInteger value;
	//BigInteger zero constant
	private BigInteger zero = BigInteger.ZERO;
	
	public BinaryMessage(BigInteger value) {
		//if value entered is null, the message should contain a zero
		if(value == null) {
			this.value = zero;
		}
		//otherwise assign it the given value
		else {
			this.value = value;
		}
	}
	
	public BigInteger getValue() {
		return this.value;
	}
	
	//returns true if the object is not null, is a BinaryMessage object, and the underlying integers are equal
	public boolean equals(Object anObject) {
		//use short circuit evaluation, so we know that if it gets to isValuesEqual test, anObject is a BinaryMessage
		if(!isNull(anObject) &&
		   isBinaryMessage(anObject) && 
		   isValuesEqual((BinaryMessage)anObject, this)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//checks if the object is null
	private boolean isNull(Object anObject) {
		if(anObject == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//checks if the object is a binary message
	private boolean isBinaryMessage(Object anObject) {
		if(anObject.getClass() == this.getClass()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//checks if the two binary messages underlying integers are equal
	private boolean isValuesEqual(BinaryMessage tocmp, BinaryMessage original) {
		if(tocmp.getValue().equals(original.getValue())) {
			return true;
		}
		else {
			return false;
		}
	}
}
