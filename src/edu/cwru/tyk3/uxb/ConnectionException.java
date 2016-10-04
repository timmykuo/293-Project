package edu.cwru.tyk3.uxb;

public class ConnectionException extends Exception{

	public enum ErrorCode{CONNECTOR_BUSY, CONNECTOR_MISMATCH, CONNECTION_CYCLE};
	
	private final Connector connector;
	private final ErrorCode errorcode;
	private static final long serialVersionUID = 293;
	
	public ConnectionException(Connector connector, ErrorCode errorcode) {
		this.connector = connector;
		this.errorcode = errorcode;
	}
	
	public Connector getConnector() {
		return this.connector;
	}
	
	public ErrorCode getErrorcode() {
		return this.errorcode;
	}
	
	
}
