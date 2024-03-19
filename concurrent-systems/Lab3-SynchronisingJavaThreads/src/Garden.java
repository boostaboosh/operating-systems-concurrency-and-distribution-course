/**
 * Garden objects are synchronised objects that simulate
 * a garden in the garden gate problem.
 * Gardens can be filled if they reach their capacity.
 */
public class Garden
{
   private final int CAPACITY;
   private int counter;

   /**
    * Constructs a garden of the specified capacity.
    * @param capacity the capacity of the garden
    */
   public Garden (int capacity)
   {
      this.CAPACITY = capacity;
   }

   /**
    * Adds a single person to this garden.
    */
   public synchronized void addPerson(String gateName)
   {
      if (counter == this.CAPACITY)
      {
         System.out.printf("%s gate's garden is full.", gateName);
      }
      else
      {
         this.counter = this.counter + 1;
         System.out.printf("%s gate's garden is now at %d/%d capacity.\n",
               gateName, this.counter, CAPACITY);
      }
   }
}
