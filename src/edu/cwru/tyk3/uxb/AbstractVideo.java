package edu.cwru.tyk3.uxb;

public abstract class AbstractVideo<T extends AbstractVideo.Builder<T>> extends AbstractPeripheral<T>{

	protected AbstractVideo(Builder<T> builder) {
		super(builder);
	}
	
	@Override
	public DeviceClass getDeviceClass() {
		return DeviceClass.VIDEO;
	}
	
	public static abstract class Builder<T> extends AbstractPeripheral.Builder<T>{

		public Builder(Integer version) {
			super(version);
		}
	}

}