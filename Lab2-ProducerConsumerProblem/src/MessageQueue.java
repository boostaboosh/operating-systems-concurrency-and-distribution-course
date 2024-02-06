import java.util.*;

/**
 * A message passing system for producer and consumer objects.
 * The message queue is unbounded, i.e., it can never be full.
 */
public class MessageQueue
{
   Vector vector;
   int currentSize;

   /**
    * Constructs a MessageQueue object.
    */
   public MessageQueue()
   {
      // TODO: implementation filled in later
      this.vector = new Vector(0, 1);
      this.currentSize = 0;
   }

   /**
    * Puts a message into the message queue.
    * @param message
    */
   public void send(Date message)
   {
      // TODO: implementation filled in later
   }

   /**
    * Reads and removes a message from the message queue.
    * @return
    */
   public Date receive()
   {
      Date message;
      // TODO: implementation filled in later
      return message;
   }
}
