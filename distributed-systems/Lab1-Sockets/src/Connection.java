import java.net.*;
import java.io.*;

public class Connection extends Thread
{
   private final Socket outputLine;

   /**
    * Constructs a connection object.
    * @param socket the socket to connect to
    */
   public Connection(Socket socket)
   {
      outputLine = socket;
   }

   public void run()
   {
      // getOutputStream runs an OutputStream object
      // allowing ordinary file IO over the socket.
      try 
      {
         // create a new PrintWriter with auto flushing
         PrintWriter pout = new PrintWriter(outputLine.getOutputStream(), true);
         // now send a message to the client
         pout.println("The Date and Time is " + new java.util.Date().toString());
         // now close the socket
         outputLine.close();
      }
      catch (java.io.IOException exception)
      {
         System.out.println(exception);
      }
   }
}
