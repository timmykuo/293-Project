package edu.cwru.tyk3.uxb;

import java.math.BigInteger;

public class SisterPrinter extends AbstractPrinter<SisterPrinter.Builder>{

	private SisterPrinter(Builder builder) {
		super(builder);
	}
	
	@Override
	//use a stringbuilder
	public void recv(StringMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		
		this.getLogger().info("Sister printer has printed the string:" + 
								buildStringMessage(message, connector));
	}
	
	private String buildStringMessage(StringMessage message, Connector connector) {
		StringBuilder builder = new StringBuilder();
		//append the string message
		builder.append("The string message is: ");
		builder.append(message.getString());
		
		//append the serial number
		if(this.getSerialNumber().isPresent()) {
			builder.append(", the printer serial number is: ");
			builder.append(this.getSerialNumber());
		}
		else {
			builder.append("The printer serial number is empty");
		}
		
		return builder.toString();
	}

	@Override
	public void recv(BinaryMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		
		this.getLogger().info("Sister printer has printed the binary message:" + 
								buildBinaryMessage(message, connector));
	}
	
	private String buildBinaryMessage(BinaryMessage message, Connector connector) {
		StringBuilder builder = new StringBuilder();
		//if value is present then add 
		if(this.getProductCode().isPresent()) {
			builder.append("The sum of the message value and the product code is ");
			builder.append(message.getValue().add(BigInteger.valueOf(this.getProductCode().get())));
		}
		else {
			builder.append(message.getValue());
		}
		
		return builder.toString();
	}

	public static class Builder extends AbstractPrinter.Builder<Builder> {

		public Builder(Integer version) {
			super(version);
		}
		
		public SisterPrinter build() {
			super.validate();
			return new SisterPrinter(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}
		
	}
}
