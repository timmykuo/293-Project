package edu.cwru.tyk3.uxb;

public abstract class AbstractPrinter<T extends AbstractPeripheral.Builder<T>> extends AbstractPeripheral<T>{

	protected AbstractPrinter(Builder<T> builder) {
		super(builder);
	}
	
	@Override
	public DeviceClass getDeviceClass() {
		return DeviceClass.PRINTER;
	}

	public static abstract class Builder<T> extends AbstractPeripheral.Builder<T>{

		public Builder(Integer version) {
			super(version);
		}
	}

}
