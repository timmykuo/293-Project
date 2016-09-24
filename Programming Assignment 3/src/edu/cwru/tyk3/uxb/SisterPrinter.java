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
		//log the stringmessage and printer serial number
		this.getLogger().info("Sister printer has printed the string:" + 
					"The string message is: " + message.getString() + 
					", the printer serial number is:" + this.getSerialNumber());
	}

	@Override
	public void recv(BinaryMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		//log sum of the message and device's productcode
		this.getLogger().info("Sister printer has printed the binary message:" + 
					"The sum of the message value and the product code is " +
					message.getValue().add(BigInteger.valueOf(this.getProductCode().get())));
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
