import java.util.*;
import java.util.logging.Logger;

/**
 * The Producer creates elements and puts them into a message
 * passing system, called the message queue.
 */
class Producer extends Thread
{
   private MessageQueue messageQueue;

   /**
    * Constructs a producer object.
    * @param messageQueue the queue which messages are put into
    */
   public Producer(MessageQueue messageQueue)
   {
      this.messageQueue = messageQueue;
   }

   /**
    * Starts a new thread of execution.
    */
   public void run()
   {
      while (true)
      {
         // produce an item & enter it into the buffer
         Date dateMessage = new Date();
         this.messageQueue.send(dateMessage);
         Logger.getGlobal().info("Producer sent a message.");
      }
   }
}