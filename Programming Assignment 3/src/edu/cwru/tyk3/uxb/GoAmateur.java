package edu.cwru.tyk3.uxb;

public class GoAmateur extends AbstractVideo<GoAmateur.Builder>{

	private GoAmateur(Builder builder) {
		super(builder);
	}
	
	@Override
	public void recv(StringMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		this.getLogger().info("GoAmateur does not understand string messages:" + 
							"The message string is: " + message.getString() + 
							", the connector index is " + connector.getIndex());
	}

	@Override
	public void recv(BinaryMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		this.getLogger().info("GoAmateur is not yet active: " + 
							"The message value is " + message.getValue());
	}

	public static class Builder extends AbstractVideo.Builder<Builder> {

		public Builder(Integer version) {
			super(version);
		}
		
		public GoAmateur build() {
			super.validate();
			return new GoAmateur(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}
		
	}
}