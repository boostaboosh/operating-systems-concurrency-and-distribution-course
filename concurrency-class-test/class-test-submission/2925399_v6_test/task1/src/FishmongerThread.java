
import java.util.*;


public class FishmongerThread extends Thread
{
	private FishmongerShop shop;
	private Random random;

	public FishmongerThread(FishmongerShop shop) {
		this.shop = shop;
		this.random = new Random();
	}

	public void run ()
	{
      try
      {
         serveCustomers(120);
      } catch (InterruptedException e)
      {
         throw new RuntimeException(e);
      }
   }

	public void serveCustomers(int numberOfCustomersToServe) throws InterruptedException
	{
      for(int servedCustomersCounter = 0; servedCustomersCounter < numberOfCustomersToServe; servedCustomersCounter++)
		{
			Date customerEntryTime = this.shop.serveCustomer(servedCustomersCounter + 1);
			try
			{
				Thread.sleep(random.nextInt(300));
			}
			catch (InterruptedException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
