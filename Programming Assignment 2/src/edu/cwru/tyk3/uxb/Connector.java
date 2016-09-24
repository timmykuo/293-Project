package edu.cwru.tyk3.uxb;
import java.util.Optional;

public class Connector {

	public enum Type {COMPUTER, PERIPHERAL};
	
	private final int index;
	private final Type type;
	private Optional<Connector> peer;
	private final Device device;
	
	//connector constructor only sets device, index, and type. does not set peer
	public Connector(Device device, int index, Type type) {
		this.device = device;
		this.index = index;
		this.type = type;
	}
	
	public Device getDevice() {
		return this.device;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Optional<Connector> getPeer() {
		return this.peer;
	}
	
	
}
