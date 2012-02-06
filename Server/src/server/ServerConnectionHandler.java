package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnectionHandler implements Runnable {

	private Socket clientSocket;
	
	public ServerConnectionHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
	
		System.out.println("Client " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " has connected.");

		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String inputline, outputline;
			outputline = "Connected to server...type in the console to interact!";
			out.println(outputline);
			
			while((inputline = in.readLine()) != null){
				outputline = "Server echoes: " + inputline;
				out.println(outputline);
			}
		}
		
		catch(Exception e) {
			System.out.println("Client " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " has disconnected.");
		}
	}
	
	
}
