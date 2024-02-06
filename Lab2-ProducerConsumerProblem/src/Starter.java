import java.util.logging.*;

/**
 * Starts the Producer-Consumer problem.
 */
public class Starter
{
   /**
    * Starts the program.
    * @param args the program arguments
    */
   public static void main(String[] args)
   {
      Logger.getGlobal().setLevel(Level.INFO);
      Logger.getGlobal().info("Logger level is " + Logger.getGlobal().getLevel());

      Producer producerThread = new Producer();
      Consumer consumerThread = new Consumer();
   }
}
