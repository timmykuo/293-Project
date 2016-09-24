package edu.cwru.tyk3.uxb;

public interface Message {

	//signifies that the Message has reached the given device from the given connector
	void reach(Device device, Connector connector);
}
