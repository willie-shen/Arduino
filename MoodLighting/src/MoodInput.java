

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Servlet implementation class MoodInput
 */
@WebServlet("/MoodInput")
public class MoodInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private 	static Connection conn = null;

	private static SerialPort comPort;

	private String[] moodMappings = new String[21];
//	private byte[][] valueToArduino;

    /**
     * Default constructor. 
     */
    public MoodInput() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //We want to make a thread delay whenever we make a change since there will be a delay for the arduino 
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("Clicked");
		int moodValue = -1;
		try {
			 moodValue = Integer.parseInt((String) request.getParameter("moodValue"));
		}catch(NumberFormatException e) {
			System.out.println("Bad format: will default to something random");
		}
		
		
		System.out.println("Your mood is " + moodValue);
		
		if(moodValue <= 20 && moodValue >=0)
			addMoodToTable(moodValue, moodMappings[moodValue]);
		
		else
			addMoodToTable(moodValue, moodMappings[20]);
		
	/*	try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Starting Servlet");
		//comPort = SerialPort.getCommPorts()[4]
	//	comPort.openPort();
		moodMappings[0] = "purr";
		moodMappings[1] = "happy";
		moodMappings[2] = "sad";
	//	valueToArduino = new Byte[21];
		//for(int i = 0; i<21; i++) {
		//	valueToArduino[i] = new byte[1];
	//		valueToArduino[i][0] = (byte) i;
	//	}
				
		for(int i = 3; i<=20; i++) {
			moodMappings[i] = "ColorBlend";
		}
		//Connect to the database
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {//moodlighting.cz5aiacvevw3.us-west-2.rds.amazonaws.com
			conn = DriverManager.getConnection("jdbc:mysql://moodlighting.cz5aiacvevw3.us-west-2.rds.amazonaws.com:3306/MoodLighting?useSSL=false&user=willieshen&password=password");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		super.init();
	}



public void addMoodToTable(int value, String mood) {
	
	Calendar cal = Calendar.getInstance();

	String addToTable = "INSERT into MoodUpdate(moodDate, moodTime, moodValue, mood) VALUES (?, ?, ?, ?)";
	
	java.sql.PreparedStatement st = null;
	
	try {
		st = conn.prepareStatement(addToTable);
		
		DateFormat dateFormat0 = new SimpleDateFormat("MMM dd, YYYY");
		String date = dateFormat0.format(new Date()).toString();		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
		String time = dateFormat.format(new Date()).toString();
		
		st.setString(1, date);
		st.setString(2, time);
		st.setInt(3, value);
		st.setString(4, mood);
		st.execute();
	}catch(SQLException e) {
		e.printStackTrace();
	}
}

public void sendToArduino(int value) {
	
	
}
}