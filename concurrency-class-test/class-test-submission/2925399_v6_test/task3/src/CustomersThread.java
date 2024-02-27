import java.util.*;

public class CustomersThread extends Thread
{
	private FishmongerShops shops;
	private Random random;

	public CustomersThread(FishmongerShops shops)
	{
		this.shops = shops;
		this.random = new Random();
	}

	public void run()
	{
      try
      {
         enterShop(120);
      } catch (InterruptedException e)
      {
         throw new RuntimeException(e);
      }
   }

	public void enterShop(int numberOfCustomersToEnterShop) throws InterruptedException
	{
		for(int customerEntriesCounter = numberOfCustomersToEnterShop; customerEntriesCounter > 0; customerEntriesCounter--)
		{
			// Time of customer customerEntriesCounter's arrival is recoded in the shop
			// can be used to compute service times for each customer
			Date time = new Date();
			this.shops.enterShop(time);
			try
			{
				Thread.sleep(random.nextInt(500));
			}
			catch (InterruptedException e)
			{
				System.out.println(e.getMessage());
			}

		}
	}
}
