package edu.cwru.tyk3.uxb;

public class StringMessage implements Message {
	
	private final String string;

	public StringMessage(String string) {
		if(string == null) {
			this.string = "";
		}
		else {
			//create a copy of the input string
			String copy = new String(string);
			this.string = copy;
		}
	}
	
	public String getString() {
		return string;
	}
	
	@Override
	public void reach(Device device, Connector connector) {
		device.recv(this, connector);
	}
	
	/*
	 * Returns true if and only if:
	 * (1) The argument is not null
	 * (2) The argument is a StringMessage object
	 * (3) The underlying strings are equal
	 */
	public boolean equals(Object anObject) {
		return (!isNull(anObject) &&
				isStringMessage(anObject) &&
				isStringsEqual((StringMessage)anObject, this));
	}

	private boolean isStringsEqual(StringMessage message1, StringMessage message2) {
		return (message1.getString().equals(message2.getString()));
	}

	private boolean isStringMessage(Object anObject) {
		return (anObject.getClass().equals(this.getClass()));
	}

	private boolean isNull(Object anObject) {
		return (anObject == null);
	}
	
	public int length() {
		return this.string.length();
	}
	
	public char charAt(int index) {
		return this.string.charAt(index);
	}
	
	public boolean contains(String CharSequence) {
		return this.string.contains(CharSequence);
	}
	
	public boolean endsWith(String suffix) {
		return this.string.endsWith(suffix);
	}
	
	public boolean startsWith(String prefix) {
		return this.string.startsWith(prefix);
	}
	
	public int indexOf(int ch) {
		return this.string.indexOf(ch);
	}
	
	public int indexOf(int ch, int fromIndex) {
		return this.string.indexOf(ch, fromIndex);
	}
	
	public int indexOf(String str) {
		return this.string.indexOf(str);
	}
	
	public int indexOf(String str, int fromIndex) {
		return this.string.indexOf(str, fromIndex);
	}
	
	public int lastIndexOf(int ch) {
		return this.string.indexOf(ch);
	}
	
	public int lastIndexOf(int ch, int fromIndex) {
		return this.string.indexOf(ch, fromIndex);
	}
	
	public int lastIndexOf(String str) {
		return this.string.indexOf(str);
	}
	
	public int lastIndexOf(String str, int fromIndex) {
		return this.string.indexOf(str, fromIndex);
	}
	
	public boolean isEmpty() {
		return this.string.isEmpty();
	}
	
	public int hashCode() {
		return this.string.hashCode();
	}
}
