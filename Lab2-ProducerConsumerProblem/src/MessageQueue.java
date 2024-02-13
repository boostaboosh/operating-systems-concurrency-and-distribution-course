import java.util.*;

/**
 * A message passing system for producer and consumer objects.
 * The message queue is unbounded, i.e., it can never be full.
 */
public class MessageQueue
{
   Vector<Date> vector;
   int currentSize;

   /**
    * Constructs a MessageQueue object.
    */
   public MessageQueue()
   {
      this.vector = new Vector<Date>(0, 1);
      this.currentSize = 0;
   }

   /**
    * Puts a message into the message queue.
    * @param dateMessage the message to add to the message queue
    */
   public void send(Date dateMessage)
   {
      this.vector.add(dateMessage);
      currentSize = currentSize + 1;
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
         currentSize = currentSize - 1;
         return dateMessage;
      }
   }
}
