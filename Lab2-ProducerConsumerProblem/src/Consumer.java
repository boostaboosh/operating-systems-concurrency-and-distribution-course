import java.util.*;
import java.util.logging.Logger;

/**
 * The Consumer reads and removes elements from the message queue and
 * displays the information on the screen.
 */
class Consumer extends Thread
{
   private final MessageQueue messageQueue;

   /**
    * Constructs a consumer object.
    * @param messageQueue the queue messages are read from
    */
   public Consumer(MessageQueue messageQueue)
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
         Date dateMessage = this.messageQueue.receive();
         Logger.getGlobal().info("Consumer received message.");
         if (dateMessage != null)
         {
            // consume the dateMessage
            System.out.println(dateMessage.toString());
         }
      }
   }
}