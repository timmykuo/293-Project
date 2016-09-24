package edu.cwru.tyk3.uxb;
import java.util.List;
import java.util.Optional;
import java.math.BigInteger;

public interface Device {

	//returns the product code, or empty optional if unknown
	public Optional<Integer> getProductCode();
	//returns the serial number, or empty optional if unknown
	public Optional<BigInteger> getSerialNumber();
	//returns the UXB version the device supports
	public Integer getVersion();
	//returns the UXB device's class
	public DeviceClass getDeviceClass();
	//returns the number of connectors the device has
	public Integer getConnectorCount();
	//returns the type of each connector in this device
	public List<Connector.Type> getConnectors();
	//returns the connector of this device at the given index
	public Connector getConnector(int index);
	
}
