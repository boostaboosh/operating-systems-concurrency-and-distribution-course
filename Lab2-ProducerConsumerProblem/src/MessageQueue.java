import java.util.*;
import java.util.logging.Logger;

/**
 * A message passing system for producer and consumer objects.
 * The message queue is unbounded, i.e., it can never be full.
 */
public class MessageQueue
{
   Vector<Date> vector;
   final int MAX_SIZE = 10;

   /**
    * Constructs a MessageQueue object.
    */
   public MessageQueue()
   {
      this.vector = new Vector<>();
   }

   /**
    * Puts a message into the message queue if the current size is less than MAX_SIZE.
    * @param dateMessage the message to add to the message queue
    */
   public void send(Date dateMessage) {
      if (this.vector.size() < MAX_SIZE) {
         this.vector.add(dateMessage);
      } else {
         Logger.getGlobal().info("Could not add message because queue is full!");
         // This handles the case non-blockingly by simply not adding new messages when the queue is full.
      }
   }


   /**
    * Reads and removes a message from the message queue.
    * @return the next date in the message queue
    */
   public Date receive()
   {
      if (this.vector.isEmpty()) {
         // Optionally, you could log this situation or wait until an item is available.
         return null; // Return null or handle this scenario appropriately.
      }
      else
      {
         Date dateMessage;
         dateMessage = this.vector.remove(0);
         return dateMessage;
      }
   }
}
