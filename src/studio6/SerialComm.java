package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
	
	public void writeByte(byte b){
		try {
			port.writeByte(b);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (debug){
			/*int first = b/16;
			//System.out.println(b);
			//System.out.println(first);
			char firstChar;
			if(first<10){
				firstChar = (char) (first+0x30);
			} else {
				firstChar = (char) (first+0x60);
			}
			int second = b%16;
			//System.out.println(second);
			char secondChar;
			if(second<10){
				secondChar = (char) (second+0x30);
			} else {
				secondChar = (char) (second+0x60-9);
			}
			System.out.println("<0x"+firstChar+secondChar+">");*/
			System.out.println("<0x"+String.format("%02x", b)+">");
			
		}
	}
		
	// TODO: Add writeByte() method from Studio 5
	
	public boolean available() {
		try {
			return port.getInputBufferBytesCount()>0;
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// TODO: Add available() method
	
	byte readByte() {
		try {
			byte b =  port.readBytes()[0];
			if(debug) {
				System.out.println("<0x"+String.format("%02x", b)+">");
			}
			return b;
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	// TODO: Add readByte() method	
	
	// TODO: Add a main() method
	public static void main(String[] args) {
		SerialComm x;
		try {
			x = new SerialComm("COM7");
		
			while(true) {
				if(x.available()) {
					System.out.print((char)x.readByte());
				}
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
