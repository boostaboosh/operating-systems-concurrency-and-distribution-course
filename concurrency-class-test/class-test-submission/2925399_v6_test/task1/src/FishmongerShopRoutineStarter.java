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
      final int SHOP_CAPACITY = 4;
      FishmongerShop fishmongerShop = new FishmongerShop(SHOP_CAPACITY);

      FishmongerThread fishmongerThread = new FishmongerThread(fishmongerShop);
      CustomersThread customersThread = new CustomersThread(fishmongerShop);

      fishmongerThread.start();
      customersThread.start();
   }
}
