import java.net.*;
import java.io.*;

public class Client
{
   /**
    * Constructs a client object.
    */
   public Client()
   {
      try
      {
         Socket socket = new Socket("127.0.0.1", 5155);
         InputStream inputStream = socket.getInputStream();
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
         System.out.println(bufferedReader.readLine());
         socket.close();
      }
      catch (java.io.IOException exception)
      {
         System.out.println(exception);
         System.exit(1);
      }
   }

   public static void main(String[] args)
   {
      Client client = new Client();
   }
}
