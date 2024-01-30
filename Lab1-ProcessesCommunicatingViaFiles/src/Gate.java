import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A gate counts people let through.
 */
public class Gate
{
   private final GatePosition gatePosition;
   private RandomAccessFile entriesCountFile;

   /**
    * Creates a gate object and returns a reference to its location in memory.
    */
   public Gate(GatePosition gatePosition)
   {
      this.gatePosition = gatePosition;
      try
      {
         this.entriesCountFile = new RandomAccessFile("admin.txt","rw");
      }
      catch (IOException e)
      {
         System.out.println("something wrong with file access" + e);
      }
   }

   /**
    * Increments the count of people in the garden by one.
    */
   public void counting()
   {
      long thisGatePointerPosition = 0;
      long otherGatePointerPosition = 1;
      if (this.gatePosition == GatePosition.TOP)
      {
         thisGatePointerPosition = 1;
         otherGatePointerPosition = 0;
      }
      try
      {
         // read other gates counter value in the file
         entriesCountFile.seek(otherGatePointerPosition);
         byte[] otherGateEntriesCounterByteArray = new byte[1];
         entriesCountFile.read(otherGateEntriesCounterByteArray);
         byte otherGateCurrentCount = otherGateEntriesCounterByteArray[0];

         // read this gate's counter value in the file
         entriesCountFile.seek(thisGatePointerPosition);
         byte[] thisGateEntriesCounterByteArray = new byte[1];
         entriesCountFile.read(thisGateEntriesCounterByteArray);
         Thread.sleep(500); // slowing the execution down by 500ms after
                                  // reading the value from the file
         byte thisGateCurrentCount = thisGateEntriesCounterByteArray[0];

         // getting the total count of people who have entered both gates
         int total = otherGateCurrentCount + thisGateCurrentCount;
         System.out.println("bottom and top gates entries count: " + total);

         // write incremented gatePosition gate counter value to the file
         byte incrementedCount = (byte) (thisGateCurrentCount + 1);
         thisGateEntriesCounterByteArray[0] = incrementedCount;
         entriesCountFile.seek(thisGatePointerPosition);
         entriesCountFile.write(thisGateEntriesCounterByteArray);
         Thread.sleep(0); // slowing the execution down by 500ms after
                                  // writing the value to the file
         System.out.println(gatePosition + " incremented entries count: "
               + incrementedCount);

         // getting the new total count of people who have entered both gates
         total = otherGateCurrentCount + incrementedCount;
         System.out.println("bottom and top gates entries count: " + total);
      }
      catch (IOException e)
      {
         System.out.println("something wrong with file access" + e);
      }
      catch (InterruptedException e) // catch for the sleep method
      {
         throw new RuntimeException(e);
      }
   }
}
