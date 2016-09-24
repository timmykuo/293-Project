package edu.cwru.tyk3.uxb;

public class Hub extends AbstractDevice<Hub.Builder>{

	private Hub(Builder builder) {
		super(builder);
	}

	@Override
	public DeviceClass getDeviceClass() {
		return DeviceClass.HUB;
	}
	
	@Override
	public void recv(StringMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		//otherwise log the informational message
		this.getLogger().info("recv not yet supported");
	}

	@Override
	public void recv(BinaryMessage message, Connector connector) {
		//validate to see if message/connector is null and if the connector belongs to the device
		super.validate(message, connector);
		//otherwise log the informational message
		this.getLogger().info("recv not yet supported");
	}

	public static class Builder extends AbstractDevice.Builder<Builder> {
		public Builder(Integer version) {
			super(version);
		}
		
		public Hub build() {
			//check if there is a version number, there are computers connected, and there is at least one peripheral
			this.validate();
			//use builder's version, productcode, etc.
			return new Hub(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}
		
		protected void validate() {
			if(getThis().getVersion() == null) { 
				throw new IllegalStateException("There is no version number for this Hub.");
			}
			else if(!existType(Connector.Type.COMPUTER)) {
				throw new IllegalStateException("There are no computer connectors to this Hub.");
			}
			else if(!existType(Connector.Type.PERIPHERAL)) {
				throw new IllegalStateException("There are no peripheral connectors in this Hub.");
			}
		}
		
		//returns false if there are peripherals, true if there aren't
		private boolean existType(Connector.Type test) {
			for(Connector.Type t : this.getConnectors()) {
				if(t == test) {
					return true;
				}
			}
			return false;
		}
	}
}
