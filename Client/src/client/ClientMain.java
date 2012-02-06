package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
	
	private String host;
	private int port;
	
	public ClientMain(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void start() {
		try {
			Socket clientSocket = new Socket(host, port);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String inputline, outputline;
				while((inputline = in.readLine()) != null){
					System.out.println(inputline);
					outputline = getUserInput();
					out.println(outputline);
				}
		} 
		
		catch (Exception e) {
			System.out.println("Server not running, shutting down...");
			System.exit(-1);
		} 
	}

	private static String getUserInput() {
		String s = "";
		InputStreamReader isr; 
		BufferedReader br;
		try {
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			s += br.readLine();
			
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	
		return s;
	}
}
