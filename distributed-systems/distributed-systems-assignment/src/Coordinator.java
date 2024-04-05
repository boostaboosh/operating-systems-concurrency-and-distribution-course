import java.net.*;

/**
 * Central server that manages distribution of the token.
 * Listens for token requests from nodes and sends the 
 * token to one node at a time through TCP connection, 
 * ensuring that only one node enters its critical section.
 */
public class Coordinator {
	public static void main(String[] args) 
	{
		int port = 7000;
		Coordinator coordinator = new Coordinator();

		try 
		{
			InetAddress coordinatorAddress = InetAddress.getLocalHost();
			String coordinatorAddressHostName = coordinatorAddress.getHostName();
			System.out.println("Coordinator address is " + coordinatorAddress);
			System.out.println("Coordinator host name is " + coordinatorAddressHostName + "\n\n");
		} catch (Exception exception) 
		{
			System.err.println(exception);
			System.err.println("Error in coordinator");
		}

		// allows defining port at launch time
		if (args.length == 1)
		{
			port = Integer.parseInt(args[0]);
		}

		// Create and run a C_receiver and a C_mutex object sharing a C_buffer object
		
		// Create a shared buffer for storing requests
		RequestQueue sharedBuffer = new RequestQueue();

		// Initialize and start the receiver thread
		TokenRequestReceiver receiver = new TokenRequestReceiver(sharedBuffer, port);
		receiver.start();

		// Initialize and start the mutex thread
		TokenDistributor mutex = new TokenDistributor(sharedBuffer, 7001); // Assuming 7001 is the port for token return
		mutex.start();
	}
}
