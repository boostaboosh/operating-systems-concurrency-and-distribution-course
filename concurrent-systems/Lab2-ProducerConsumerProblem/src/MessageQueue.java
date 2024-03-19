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
    * Inserts a message into the queue.
    * Blocks if the queue is full until space is available.
    * @param dateMessage The message to be added to the queue.
    */
   public synchronized void send(Date dateMessage) {
      if (this.vector.size() < MAX_SIZE) {
         this.vector.add(dateMessage);
         notifyAll(); // Notify any waiting consumers that a new message is available.
      } else
      {
         try
         {
            this.wait(); // Wait for space to become available.
         } catch (InterruptedException e)
         {
            throw new RuntimeException(e);
         }
      }
   }


   /**
    * Removes and returns the oldest message from the queue.
    * Blocks if the queue is empty until a message is available.
    * @return The oldest message from the queue.
    */
   public synchronized Date receive()
   {
      while (this.vector.isEmpty())
      {
         try
         {
            this.wait(); // Wait for a message to become available.
         }
         catch (InterruptedException e)
         {
            return null; // Return null or handle it differently if interruption is not acceptable
         }
      }
      // At this point the queue is guaranteed not to be empty
      Date dateMessage = this.vector.remove(0);
      notifyAll(); // Notify any waiting producers that space has become available.
      return dateMessage;
   }
}
