import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Manages the lifecycle of a client node's request for the token from the
 * coordinator, usage of the token (simulated critical section), 
 * and returning the token.
 * A node is a client that requests access to the critical section by asking
 * the coordinator for the token.
 * Upon receiving the token, the node executes its critical section
 * by simulating work though sleeping for a random duration, and 
 * then returns the token to the coordinator.
 * Each node must be able to request the token from the coordinator 
 * by sending its IP and port, wait for the token, and perform
 * its critical section of work upon receiving the token (simulated
 * by sleeping for a random duration), and return the token to the 
 * coordinator.
 */
public class Node 
{
	private Random randomNumberGenerator;
	private Socket socket; // This will be used to send the token request and return the token
	private PrintWriter socketOut = null; // used to send data over the socket
	private ServerSocket nodeServerSocket; // This listens for the token. This is the server socket for this node.
	private Socket tokenReceptionSocket; // This socket is for receiving the token
	String coordinatorHost = "127.0.0.1";
	int coordinatorRequestPort = 7000;
	int coordinatorReturnPort = 7001;
	// String nodeHost = "127.0.0.1";
	String nodeHostName;
	int nodePort;

	public Node(String name, int port, int sec) throws InterruptedException
   {
		this.randomNumberGenerator = new Random();
		this.nodeHostName = name;
		this.nodePort = port;

		System.out.println("Node " + nodeHostName + ":" + nodePort + " of DME is active ....");

		try {
			// Opens a server socket to listen for the token
			nodeServerSocket = new ServerSocket(nodePort);
		} catch (IOException e) {
			System.out.println("Error creating server socket: " + e.getMessage());
			return;
		}

		while (true) 
		{
			// >>>  sleep a random number of seconds linked to the initialisation
			// sec value
			Thread.sleep(randomNumberGenerator.nextInt(sec * 1000)); // Sleep for a random time up to 'sec' seconds

			try
			{
				// NODE sends n_host and n_port through a socket s to the coordinator
				// c_host:c_req_port
				// and immediately opens a server socket through which will receive
				// a TOKEN (actually just a synchronization).
				// **** Send to the coordinator a token request.
				socket = new Socket(coordinatorHost, coordinatorRequestPort);
				socketOut = new PrintWriter(socket.getOutputStream(), true);
				// send your ip address and port number
				// Sending this node's hostname and port as the token request
				socketOut.println(nodeHostName + ":" + nodePort);
				socket.close(); // Close the connection after sending the request

				// **** Then Wait for the token
				// Print suitable messages
				System.out.println("Waiting for the token...");
				tokenReceptionSocket = nodeServerSocket.accept(); // Blocks until the coordinator sends the token
				System.out.println("Token received, entering critical section.");

				// **** Sleep for a while
				// This simulates the critical section
				int sleepTime = sec + randomNumberGenerator.nextInt(sec);
				Thread.sleep(sleepTime * 1000); // Sleep for sec to 2*sec seconds
				System.out.println("Critical section completed, returning the token.");

				// **** Return the token
				// Print suitable messages - also considering communication failures
				socket = new Socket(coordinatorHost, coordinatorReturnPort);
				socketOut = new PrintWriter(socket.getOutputStream(), true);
				// Indicate the token is being returned
				socketOut.println("Returning token from " + nodeHostName + ":" + nodePort);
				socket.close(); // Close the connection after returning the token
			} catch (IOException | InterruptedException exception) 
			{
				System.out.println("Exception in node operation: " + exception.getMessage());
				exception.printStackTrace();
				System.exit(1);
				// Exiting the loop and ending the node in case of exception
				break;
			}
		}
	}

	public static void main(String[] args) 
	{
		// port and milliseconds (average waiting time) are specific of a node
		if ((args.length < 1) || (args.length > 2)) 
		{
			System.out.print("Usage: Node [port number] [milliseconds]");
			System.exit(1);
		}
		
		int nodePort = Integer.parseInt(args[0]);
		int sec = Integer.parseInt(args[1]);

		// get the IP address and the port number of the node
		try 
		{
			InetAddress nodeInternetAddress = InetAddress.getLocalHost();
			String nodeHostName = nodeInternetAddress.getHostName();
			System.out.println("Starting node. Node hostname: " + nodeHostName 
					+ ":" + nodeInternetAddress + ", Port: " + nodePort);
			Node node = new Node(nodeHostName, nodePort, sec);
		} catch (UnknownHostException | InterruptedException exception) 
		{
			System.out.println("Unable to determine localhost name: " + exception.getMessage());
			System.out.println(exception);
			System.exit(1);
		}
	}
}
