/**
 * This class contains a program which starts the fishmonger
 * and customer shopping routine.
 */
public class FishmongerShopRoutineStarter
{
   /**
    * Starts the program.
    * @param args the program starting arguments
    */
   public static void main(String[] args)
   {
      final int SHOP_ONE_CAPACITY = 1;
      final int SHOP_TWO_CAPACITY = 3;
      FishmongerShops fishmongerShops = new FishmongerShops(SHOP_ONE_CAPACITY, SHOP_TWO_CAPACITY);

      FishmongerThread fishmongerThread = new FishmongerThread(fishmongerShops);
      CustomersThread customersThread = new CustomersThread(fishmongerShops);

      fishmongerThread.start();
      customersThread.start();
   }
}
