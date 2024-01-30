import java.io.IOException;
import java.io.RandomAccessFile;

public class garden_gate_problem
{
   static public void main(String[] args) throws IOException
   {
      RandomAccessFile admin;
      GatePosition gatePosition;

      byte[] init = {0, 0};

      if (args.length != 1)
      {
         System.err.println("usage: java {gate_bottom,gate_top}");
      }
      else
      {
         if (args[0].compareToIgnoreCase("gate_bottom") ==0)
         {
            gatePosition = GatePosition.BOTTOM;
            System.out.println("running the bottom gate");
            try
            {
               admin = new RandomAccessFile("admin.txt","rw");
               admin.seek(0);
               admin.write(init);
               admin.close();
            }
            catch (IOException e)
            {
               System.out.println("something wrong with file access" + e);
            }
         }
         else
         {
            gatePosition = GatePosition.TOP;
            System.out.println("running the top gate");
         }

         Gate gateCounter = new Gate(gatePosition);
         for (int counter = 0; counter < 50; counter++)
         {
            gateCounter.counting();
         }
      }
   }
}
