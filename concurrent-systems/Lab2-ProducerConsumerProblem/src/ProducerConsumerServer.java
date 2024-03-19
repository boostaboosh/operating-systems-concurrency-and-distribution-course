import java.util.logging.*;

/**
 * Starts the Producer-Consumer problem.
 */
public class ProducerConsumerServer
{
   /**
    * Constructs an instance of a producer-consumer server.
    */
   public ProducerConsumerServer()
   {
      MessageQueue messageQueue = new MessageQueue();

      Producer producerThread = new Producer(messageQueue);
      Consumer consumerThread = new Consumer(messageQueue);

      producerThread.start();
      consumerThread.start();
   }

   /**
    * Starts the program.
    * @param args the program arguments
    */
   public static void main(String[] args)
   {
      Logger.getGlobal().setLevel(Level.INFO);
      Logger.getGlobal().info("Logger level is " + Logger.getGlobal().getLevel());

      ProducerConsumerServer producerConsumerServer = new ProducerConsumerServer();
   }
}
