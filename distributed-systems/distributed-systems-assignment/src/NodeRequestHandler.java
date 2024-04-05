import java.io.*;
import java.net.*;
// Reacts to a node request.
// Receives and records the node request in the buffer.
//
public class NodeRequestHandler extends Thread 
{
	// class variables
	RequestQueue connectionRequestsBuffer;
	Socket socket;
	InputStream inputStream;
	BufferedReader bufferedReader;

	public NodeRequestHandler(Socket socket, RequestQueue buffer) 
	{
		this.socket = socket;
		this.connectionRequestsBuffer = buffer;
	}

	public void run() 
	{
		final int NODE = 0;
		final int PORT = 1;

		String[] request = new String[2];

		System.out.println(
				"C:connection IN  dealing with request from socket " + socket);
		try {
			// >>> read the request, i.e. node ip address and port from the socket called socket
			inputStream = socket.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			String nodeInfo = bufferedReader.readLine(); // read the node's info
			String[] requestParts = nodeInfo.split(":");
			request[NODE] = requestParts[0];
			request[PORT] = requestParts[1];

			// >>> save the requesting node's information in a request object (array) 
			// and save the object in the requests buffer
			// (see C_buffer's methods).
			connectionRequestsBuffer.saveRequest(requestParts);

			socket.close();
			System.out.println("C:connection OUT    received and recorded request from " 
					+ request[NODE] + ":" + request[PORT] + "  (socket closed)");

		} catch (IOException ioException) {
			System.out.println(ioException);
			System.exit(1);
		}
		connectionRequestsBuffer.show();
	}
}
