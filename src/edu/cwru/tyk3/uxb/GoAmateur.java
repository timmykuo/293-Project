package edu.cwru.tyk3.uxb;

import java.math.BigInteger;

public class GoAmateur extends AbstractVideo<GoAmateur.Builder>{

	private GoAmateur(Builder builder) {
		super(builder);
	}
	
	@Override
	public void recv(StringMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		
		this.getLogger().warning("GoAmateur does not understand string messages:" + 
									buildStringMessage(message, connector));
	}
	
	private String buildStringMessage(StringMessage message, Connector connector) {
		StringBuilder builder = new StringBuilder();
		
		//append stringmessage
		builder.append("The string message is: ");
		builder.append(message.getString());
		
		//append connector index
		builder.append(", the connector index is ");
		builder.append(connector.getIndex());
		
		return builder.toString();
	}

	@Override
	public void recv(BinaryMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		
		for(Connector c : this.getConnectors()) {
			if(c.getPeer().isPresent()) {
				c.getPeer().get().recv(new BinaryMessage(BigInteger.valueOf(293)));
			}
		}
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