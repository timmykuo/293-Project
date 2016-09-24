package edu.cwru.tyk3.uxb;

public class CannonPrinter extends AbstractPrinter<CannonPrinter.Builder>{

	private CannonPrinter(Builder builder) {
		super(builder);
	}
	
	@Override
	public void recv(StringMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		//log the message string and version number
		this.getLogger().info("Cannon printer has printed the string: " + 
						   "The string message is: " + message.getString() + 
						   ", the UXB version number is " + this.getVersion());
	}

	@Override
	public void recv(BinaryMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		//log the product of the binary message and the device's serial number
		this.getLogger().info("Cannon printer has printed the binary message: " + 
						   "The product of the message value and the serial number is: " + 
						   	message.getValue().multiply(this.getSerialNumber().get()));
	}

	public static class Builder extends AbstractPrinter.Builder<Builder> {

		public Builder(Integer version) {
			super(version);
		}
		
		public CannonPrinter build() {
			super.validate();
			return new CannonPrinter(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}
		
	}
}