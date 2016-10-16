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
		this.peer = Optional.empty();
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
	
	public boolean isReachable(Device device) {
		return this.getDevice().isReachable(device);
	}
	
	public void setPeer(Connector peer) throws ConnectionException {
		//validate peer connections
		isNull(peer);
		validate(peer);
		
		this.peer = Optional.of(peer);
		peer.peer = Optional.of(this);
		
	}
	
	private void isNull(Connector peer) {
		if(peer == null) {
			throw new NullPointerException("The peer is null.");
		}
	}
	
	private void validate(Connector peer) throws ConnectionException{
		
		//checks if the connector already has a peer
		if(this.peer.isPresent() || peer.getPeer().isPresent()) {
			throw new ConnectionException(this, ConnectionException.ErrorCode.CONNECTOR_BUSY);
		}
		//checks if the new peer and current connector are the same type
		else if(peer.getType().equals(this.getType())) {
			throw new ConnectionException(this, ConnectionException.ErrorCode.CONNECTOR_MISMATCH);
		}
		//checks if peer can reach the connector
		else if(peer.isReachable(this.getDevice())) {
			throw new ConnectionException(this, ConnectionException.ErrorCode.CONNECTION_CYCLE);
		}
	}
	
	//makes sure that the message reaches the connector's device
	public void recv(Message message) {
		message.reach(this.device, this);
	}
}
