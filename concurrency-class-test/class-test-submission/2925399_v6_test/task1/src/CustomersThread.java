import java.util.*;

public class CustomersThread extends Thread
{
	private FishmongerShop shop;
	private Random random;

	public CustomersThread(FishmongerShop shop)
	{
		this.shop = shop;
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
			this.shop.enterShop(time);
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
