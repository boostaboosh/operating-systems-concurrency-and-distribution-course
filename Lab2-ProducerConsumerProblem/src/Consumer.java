import java.util.*;

/**
 * The Consumer reads and removes elements from the message queue and
 * displays the information on the screen.
 */
class Consumer extends Thread
{
   private MessageQueue messageQueue;

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
         Date message = this.messageQueue.receive();
         if (message != null)
         {
            // consume the message
            System.out.println(message.toString);
         }
      }
   }
}