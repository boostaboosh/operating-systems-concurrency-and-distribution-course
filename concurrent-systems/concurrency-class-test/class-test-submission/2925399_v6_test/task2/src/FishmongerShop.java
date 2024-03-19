
import java.util.ArrayList;
import java.util.Date;

public class FishmongerShop
{
	private ArrayList<Date> customerWaitingList;
	private final int SHOP_CAPACITY;

	public FishmongerShop(int shopCapacity)
	{
      SHOP_CAPACITY = shopCapacity;
      customerWaitingList = new ArrayList<Date>();
	}

	public synchronized void enterShop(Date customerEntryTime) throws InterruptedException
	{
		if (customerWaitingList.size() < SHOP_CAPACITY)
		{
			System.out.println("New Customer entered the shop."
					+ " Shop had " + customerWaitingList.size()
					+ " customers waiting to be served inside.");
			if (customerWaitingList.isEmpty())
			{
				this.notifyAll(); // customers thread notifies fishmonger that new customer entered the shop with bell press
				System.out.println("Customer rings bell.");
			}
			customerWaitingList.add(customerEntryTime);
		}
		else
		{
			System.out.println("Shop is at full capacity of " + SHOP_CAPACITY + " so customer waits outside.");
			this.wait(); // customer thread waits to be notified that shop is no longer at full capacity
		}
	}

	public synchronized Date serveCustomer(int servedCustomerNumber) throws InterruptedException
	{
		Date customerEntryTime = null;
		if (customerWaitingList.isEmpty())
		{
			System.out.println("Shop is empty. Fishmonger is resting.");
			this.wait(); // resting fishmonger waits to be alerted by the bell that a customer has entered the shop
		}

		customerEntryTime = (Date) customerWaitingList.get(0);
		customerWaitingList.remove(0);
		Date endOfCustomerServiceTime = new Date();
		long customerServiceTime = (endOfCustomerServiceTime.getTime() - customerEntryTime.getTime());

		System.out.println("Fishmonger serves customer "+ servedCustomerNumber + " in " + customerServiceTime
				+ " time units and says goodbye. Shop had " + customerWaitingList.size() + " customer/s waiting. ");
		this.notifyAll(); // fishmonger alerts customers that the previous customer has been served

		return customerEntryTime;
	}
}
