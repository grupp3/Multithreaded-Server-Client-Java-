package server;

public class ServerStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerMain server = new ServerMain(7777);
		server.start();
	}

}
