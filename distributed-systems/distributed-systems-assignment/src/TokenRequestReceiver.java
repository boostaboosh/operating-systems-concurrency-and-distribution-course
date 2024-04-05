import java.net.*;

/**
 * Handles incoming requests from nodes to the coordinator.
 * The receiver listens on a specified port for connection requests
 * from nodes, accepts these connection requests,
 * reads the request (node IP address and port),
 * and stores this request in a shared buffer.
 */
public class TokenRequestReceiver extends Thread {
	private RequestQueue buffer;
	private int port;
	private ServerSocket serverSocket;
	private Socket socketFromNode;
	private NodeRequestHandler connectionThread;

	public TokenRequestReceiver(RequestQueue buffer, int port)
	{
		this.buffer = buffer;
		this.port = port;
	}

	public void run() 
	{
      try
		{
			// >>> create the socket the server will listen to
			serverSocket = new ServerSocket(port);

			while (!Thread.currentThread().isInterrupted()) // like while true but means loop can terminate
			{
				// >>> get a new connection
				// accept incoming connections
				socketFromNode = serverSocket.accept(); 
				// represents the socket connection established between the coordinator and
				// a node when the node makes a request. Although all nodes attempt to connect
				// to the same port (the one the receiver listens on), each connection is
				// unique. When a node connects, serverSocket.accept() returns a new socket instance 
				// specifically for that connection. This allows the coordinator to manage multiple 
				// connections simulatneously, each represented by a different Socket object.

				System.out.println("C:receiver    Coordinator has received a request ...");

				// >>> create a separate thread to service the request, a
				// C_Connection_r thread.
				// handle connection in separate thread
				connectionThread = new NodeRequestHandler(socketFromNode, buffer);
				connectionThread.start();
			}
		} catch (java.io.IOException exception)
		{
			System.out.println("Receiver exception when creating a connection " + exception.getMessage());
			exception.printStackTrace();
		}
	} // end run
}
