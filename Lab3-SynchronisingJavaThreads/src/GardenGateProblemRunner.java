/**
 * This class contains a program which simulates the garden gate
 * problem by filling a garden with people which enter through
 * the garden's gates.
 */
public class GardenGateProblemRunner
{
   /**
    * Starts the program
    * @param args the program starting arguments
    */
   public static void main(String[] args)
   {
      final int CAPACITY = 100;
      Garden garden = new Garden(CAPACITY);

      final int ENTRIES_PER_GATE = 50;
      GateThread topGateThread = new GateThread("top", garden, ENTRIES_PER_GATE);
      GateThread bottomGateThread = new GateThread("bottom", garden, ENTRIES_PER_GATE);

      topGateThread.start();
      bottomGateThread.start();
   }
}
