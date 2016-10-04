package edu.cwru.tyk3.uxb;

public class CannonPrinter extends AbstractPrinter<CannonPrinter.Builder>{

	private CannonPrinter(Builder builder) {
		super(builder);
	}
	
	@Override
	public void recv(StringMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		
		this.getLogger().info("Cannon printer has printed the string: " + 
								buildStringMessage(message, connector));
	}
	
	private String buildStringMessage(StringMessage message, Connector connector) {
		StringBuilder builder = new StringBuilder();
		//append string message
		builder.append("The string message is:");
		builder.append(message.getString());
		//append version number
		builder.append(". The UXB version number is ");
		builder.append(this.getVersion());
		
		return builder.toString();
	}

	@Override
	public void recv(BinaryMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		
		this.getLogger().info("Cannon printer has printed the binary message: " + 
								buildBinaryMessage(message, connector));
	}
	
	private String buildBinaryMessage(BinaryMessage message, Connector connector) {
		StringBuilder builder = new StringBuilder();
		//if serial number is not an empty optional
		if(this.getSerialNumber().isPresent()) {
			builder.append(message.getValue().multiply(this.getSerialNumber().get()));
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