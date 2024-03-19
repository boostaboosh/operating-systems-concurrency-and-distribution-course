import java.net.*;

public class Server
{
   //instance variables
   private ServerSocket serverSocket;
   private Socket client;
   private Connection connection;

   // constructor
   /**
    * Constructs a new server object.
    */
   public Server()
   {
      // create the socket the server will listen to
      try
      {
         serverSocket = new ServerSocket(5155);
      }
      catch (java.io.IOException exception)
      {
         System.out.println(exception);
         System.exit(1);
      }

      // now listen for connections
      System.out.println("Server is listening...");
      try
      {
         while (true)
         {
            client = serverSocket.accept();

            // create a separate thread to service the client
            connection = new Connection(client);
            connection.start();
         }
      }
      catch (java.io.IOException exception)
      {
         System.out.println(exception);
      }
   }

   public static void main(String[] args)
   {
      Server timeOfDayServer = new Server();
   }
}