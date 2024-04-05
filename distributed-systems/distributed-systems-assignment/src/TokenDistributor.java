import java.io.IOException;
import java.net.*;

/**
 * The token distributor class is responsible for managing the distribution of
 * the token based on requests stored in the buffer.
 * The token distribute class is central to managing mutual exclusion in the
 * distributed system. This class ensures that only one node at a time can 
 * access the critical section by distributing a "token" to nodes that request
 * it. The mutual exclusion is inherently maintained by the token's presence:
 * because a node must possess the token to enter its critical section, and 
 * since there's only one token, only one node can enter the critical section
 * at any given time.
 * A mutex mechanism which controls access to the critical section
 * through token distribution.
 * The mutex continuously checks the receiver token request buffer 
 * for new requests. When a request is found, the mutex connects to
 * the requesting node, sends the node the token, waits for the 
 * token to be returned, and then moves on to the next request in 
 * the buffer in a FIFO manner.
 */
public class TokenDistributor extends Thread
{
	RequestQueue buffer;
	// Socket socket;
	int returnPort;

	// ip address and port number of the node requesting the token.
	// They will be fetched from the buffer
	String nodeHost;
	int nodePort;

	public TokenDistributor(RequestQueue buffer, int returnPort)
	{
		this.buffer = buffer;
		this.returnPort = returnPort;
	}

	public void run()
	{
		try
		{
			//  >>>  Listening from the server socket on port 7001
			// from where the TOKEN will be returned later.
			// ServerSocket tokenReturnListener = new ServerSocket(7001);
			// Listening from the server socket on port 7001 from where the TOKEN will be returned later.
			ServerSocket tokenReturnListener = new ServerSocket(returnPort);

			while (true)
			{
				String nodeHost = null;
				String nodePortString = null;
				int nodePort = -1;

				// >>> Print some info on the current buffer content for debugging purposes.
				// >>> please look at the available methods in C_buffer
				// Synchronize access to the buffer to safely check if it's empty and to get requests
				synchronized (buffer)
				{
					// if the buffer is not empty
					if (!buffer.isEmpty())
					{
						System.out.println("C:mutex   Buffer size is " + buffer.getSize());
						
						// >>>   Getting the first (FIFO) node that is waiting for a
						// TOKEN form the buffer
						//       Type conversions may be needed.
						// Assuming buffer holds data in pairs (host, port), extract them in sequence.
						nodeHost = (String) buffer.get(); // Get the host address
						nodePortString = (String) buffer.get(); // Get the port as String
						nodePort = Integer.parseInt(nodePortString); // Convert port to integer

						// >>>  **** Granting the token
						try
						{
							System.out.println("Granting token to node at " + nodeHost + ":" + nodePort);
							// Here you would connect to the node to grant the token. 
							// This could be a simple signal or a specific message.
							// For simplicity, just opening and closing the connection could signify token granting.
							try (Socket nodeSocket = new Socket(nodeHost, nodePort))
							{
								// Token granted. The connection is the signal for token granting.
							}
							// Assuming token reception is acknowledged immediately, else you would wait for an explicit message here.

							// **** Getting the token back
							// THIS IS BLOCKING !
							// This is blocking! Waiting for the node to return the token.
							try (Socket returnSocket = tokenReturnListener.accept())
							{
								// Token returned. This blocking accept call waits until the token is returned.
								System.out.println("Token returned by node " + nodeHost + ":" + nodePort);
							}
						} catch (IOException exception)
						{
							System.out.println(exception);
							System.out.println(
									"CRASH Mutex waiting for the TOKEN back" + exception);
							System.out.println("Error granting/returning token for node " + nodeHost + ":" + nodePort);
							System.out.println(exception.getMessage());
						}
					} else
					{
						// If the buffer is empty, wait for a request to be added
						buffer.wait(); // This requires corresponding notify()/notifyAll() when a request is added to the buffer.
					}
				} // end if
			} // end while
		} catch (InterruptedException | IOException exception)
		{
			System.out.println("TokenDistributor encountered an error: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
}
