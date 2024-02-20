/**
 * A gate objects can let people enter the garden it is
 * associated with.
 */
public class GateThread extends Thread
{
   private final String GATE_NAME;
   private final Garden garden;
   private final int NUMBER_OF_ENTRIES;

   /**
    * Constructs a gate thread objects
    * @param garden the garden this gate is associate with
    */
   public GateThread (String gateName, Garden garden, int numberOfEntries)
   {
      this.GATE_NAME = gateName;
      this.garden = garden;
      this.NUMBER_OF_ENTRIES = numberOfEntries;
   }

   /**
    * This thread of the gate class' thread of execution.
    */
   public void run()
   {
      for (int counter = 0; counter < NUMBER_OF_ENTRIES; counter++)
      {
         try
         {
            this.attemptEntry();
         } catch (InterruptedException e)
         {
            throw new RuntimeException(e);
         }
      }
   }

   /**
    * Attempts to add an entry to the garden this gate is associated with.
    */
   public void attemptEntry() throws InterruptedException
   {
      this.garden.addPerson(this.GATE_NAME);
      sleep(500);
   }
}
