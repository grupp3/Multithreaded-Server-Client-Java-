package client;

public class ClientStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientMain client = new ClientMain("localhost", 7777);
		client.start();
	}
}
