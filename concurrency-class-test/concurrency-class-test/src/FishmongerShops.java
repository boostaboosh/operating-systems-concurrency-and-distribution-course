
import java.util.ArrayList;
import java.util.Date;

public class FishmongerShops
{
	private final ArrayList<Date> shopOneWaitingList;
	private final ArrayList<Date> shopTwoWaitingList;
	private final int SHOP_ONE_CAPACITY;
	private final int SHOP_TWO_CAPACITY;

	public FishmongerShops(int shopOneCapacity, int shopTwoCapacity)
	{
      SHOP_ONE_CAPACITY = shopOneCapacity;
		SHOP_TWO_CAPACITY = shopTwoCapacity;
      shopOneWaitingList = new ArrayList<Date>();
		shopTwoWaitingList = new ArrayList<Date>();
	}

	public synchronized void enterShop(Date customerEntryTime) throws InterruptedException
	{
		if (shopOneWaitingList.size() < SHOP_ONE_CAPACITY)
		{
			System.out.println("New Customer entered shop 1."
					+ " Shop had " + shopOneWaitingList.size()
					+ " customers waiting to be served inside.");
			if (shopOneWaitingList.isEmpty())
			{
				this.notifyAll(); // customers thread notifies fishmonger that new customer entered shop 1 with bell press
				System.out.println("Customer rings bell in shop.");
			}
			shopOneWaitingList.add(customerEntryTime);
		}
		else if (shopTwoWaitingList.size() < SHOP_TWO_CAPACITY)
		{
			System.out.println("New Customer entered shop 2."
					+ " Shop had " + shopTwoWaitingList.size()
					+ " customers waiting to be served inside.");
			if (shopTwoWaitingList.isEmpty())
			{
				this.notifyAll(); // customers thread notifies fishmonger that new customer entered shop 2 with bell press
				System.out.println("Customer rings bell in shop.");
			}
			shopTwoWaitingList.add(customerEntryTime);
		}
		else
		{
			System.out.println("Shops are at full capacity of so customers waits outside.");
			this.wait();
		}
	}

	public synchronized Date serveCustomer(int servedCustomerNumber) throws InterruptedException
	{
		Date customerEntryTime = null;
		if (shopOneWaitingList.isEmpty())
		{
			System.out.println("Shop 1 is empty. Fishmonger is resting.");
			this.wait(); // resting fishmonger waits to be alerted by the bell that a customer has entered the shop
		}
		if (shopTwoWaitingList.isEmpty())
		{
			System.out.println("Shop 2 is empty. Fishmonger is resting.");
			this.wait(); // resting fishmonger waits to be alerted by the bell that a customer has entered the shop
		}

		if (shopOneWaitingList.size() != SHOP_ONE_CAPACITY)
		{
			customerEntryTime = (Date) shopOneWaitingList.get(0);
			shopOneWaitingList.remove(0);
			Date endOfCustomerServiceTime = new Date();
			long customerServiceTime = (endOfCustomerServiceTime.getTime() - customerEntryTime.getTime());

			System.out.println("Fishmonger in shop 1 serves customer "+ servedCustomerNumber + " in "
					+ customerServiceTime
					+ " time units and says goodbye. Shop had " + shopOneWaitingList.size() + " customer/s waiting. ");
			this.notifyAll(); // fishmonger alerts customers that the previous customer has been served
		}
		else if (shopTwoWaitingList.size() != SHOP_TWO_CAPACITY)
		{
			customerEntryTime = (Date) shopOneWaitingList.get(0);
			shopOneWaitingList.remove(0);
			Date endOfCustomerServiceTime = new Date();
			long customerServiceTime = (endOfCustomerServiceTime.getTime() - customerEntryTime.getTime());

			System.out.println("Fishmonger in shop 2 serves customer "+ servedCustomerNumber + " in "
					+ customerServiceTime
					+ " time units and says goodbye. Shop had " + shopOneWaitingList.size() + " customer/s waiting. ");
			this.notifyAll(); // fishmonger alerts customers that the previous customer has been served
		}


		return customerEntryTime;
	}
}
