import com.fazecast.jSerialComm.SerialPort;

public class testing {
	public static void main(String args[]) {
		System.out.println(SerialPort.getCommPorts().length);
		SerialPort comPort = SerialPort.getCommPorts()[4];
		comPort.openPort();
		
		System.out.println("Connected to Arduino");
		System.out.println("Bytes available" + comPort.bytesAvailable());
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(SerialPort.getCommPorts().length);
		
		System.out.println("Bytes available" + comPort.bytesAvailable());

		System.out.println("Done");
	}
	
	
}
