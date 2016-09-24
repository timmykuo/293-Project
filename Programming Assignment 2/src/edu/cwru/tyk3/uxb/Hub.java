package edu.cwru.tyk3.uxb;

import java.util.List;

public class Hub extends AbstractDevice<Hub.Builder>{

	public Hub(Builder builder) {
		super(builder);
	}

	@Override
	public DeviceClass getDeviceClass() {
		return DeviceClass.HUB;
	}

	@Override
	public Integer getConnectorCount() {
		// TODO Auto-generated method stub
		return null;
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
			else if(getThis().getConnectors() == null) {
				throw new IllegalStateException("There are no computer connectors to this Hub.");
			}
			else if(!existPeripherals(getThis().getConnectors())) {
				throw new IllegalStateException("There are no peripheral connectors in this Hub.");
			}
		}
		
		//returns false if there are peripherals, true if there aren't
		private boolean existPeripherals(List<Connector.Type> connectorTypes) {
			for(Connector.Type t : connectorTypes) {
				if(t == Connector.Type.PERIPHERAL) {
					return true;
				}
			}
			return false;
		}
	}
}
