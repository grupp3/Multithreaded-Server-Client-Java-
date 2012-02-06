package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain implements Runnable{

	private ServerSocket serverSocket;
	private int portNumber;
	private List<Thread> connections;
	
	public ServerMain() {
		this(8080);
	}
		
	public ServerMain(int portNumber) {
		this.portNumber = portNumber;
		connections = new ArrayList<Thread>();
		new Thread(this).start();
	}
	
	public void start() {
		try {
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Server started...");
			System.out.println("No clients currently connected.");
			
			while(true) {
				Socket clientSocket = serverSocket.accept();
				ServerConnectionHandler s = new ServerConnectionHandler(clientSocket);
				Thread t = new Thread(s);
				connections.add(t);
				t.start();
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	@Override
	public void run() {
		int previousCount = 0;
		
		while(true) {
			int currentCount = 0;
			
			for(Thread thread : connections){
				if(thread.isAlive())
					currentCount++;
			}
			
			if(currentCount != previousCount) {
				if(currentCount == 0)
					System.out.println("No clients currently connected.");
				else
					System.out.println(currentCount + " client(s) currently connected.");
			}
				
			previousCount = currentCount;
			
			try {
				Thread.sleep(100);
			} 
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
			
}
