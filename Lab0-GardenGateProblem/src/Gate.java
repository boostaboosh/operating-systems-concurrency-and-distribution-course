import java.io.*;

/**
 * A gate counts people let through.
 */
public class Gate
{
   private RandomAccessFile entriesCountFile;

   /**
    * Creates a gate object and returns a reference to its location in memory.
    */
   public Gate()
   {
      try
      {
         entriesCountFile = new RandomAccessFile("admin.txt","rw");
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
      try
      {
         entriesCountFile.seek(0);
         byte[] byteArray = new byte[1];
         entriesCountFile.read(byteArray);
         Thread.sleep(500); // slowing the execution down by 500ms after
                                  // reading the value from the file
         byte currentCount = byteArray[0];
         if (currentCount == 0)
         {
            System.out.println("starting entries count: " + currentCount);
         }

         byte incrementedCount = (byte) (currentCount + 1);
         byteArray[0] = incrementedCount;
         entriesCountFile.seek(0);
         entriesCountFile.write(byteArray);
         Thread.sleep(500); // slowing the execution down by 500ms after
                                  // writing the value to the file
         System.out.println("incremented entries count: " + incrementedCount);
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
