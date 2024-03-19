
import java.util.*;


public class FishmongerThread extends Thread
{
	private FishmongerShops shops;
	private Random random;

	public FishmongerThread(FishmongerShops shops) {
		this.shops = shops;
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
			Date customerEntryTime = this.shops.serveCustomer(servedCustomersCounter + 1);
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
