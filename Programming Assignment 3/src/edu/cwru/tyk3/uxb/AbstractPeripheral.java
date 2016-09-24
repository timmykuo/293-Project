package edu.cwru.tyk3.uxb;

public abstract class AbstractPeripheral<T extends AbstractPeripheral.Builder<T>> extends AbstractDevice<T>{

	protected AbstractPeripheral(Builder<T> builder) {
		super(builder);
	}
	
	public static abstract class Builder<T> extends AbstractDevice.Builder<T>{

		public Builder(Integer version) {
			super(version);
		}

		@Override
		/*
		 * Validate throws an exception if the following are true:
		 * (1) The version number is null
		 * (2) There is a connector of type COMPUTER in the list
		 */
		public void validate() {
			super.validate();
			if(!allPeripherals()) {
				throw new IllegalStateException("All connectors have to be of type PERIPHERALL.");
			}
			else {
				//no other exceptions left to throw, so do nothing
			}
		}
		
		//returns false if there are computers, true if there aren't
		private boolean allPeripherals() {
			for(Connector.Type t : this.getConnectors()) {
				if(t.equals(Connector.Type.COMPUTER)) {
					return false;
				}
			}
			return true;
		}
	}

}
