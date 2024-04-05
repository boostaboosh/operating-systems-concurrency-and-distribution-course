import java.util.*;

public class RequestQueue 
{
	private Vector<Object> data;

	public RequestQueue() 
	{
		data = new Vector<Object>();
	}

	public int getSize() 
	{
		return data.size();
	}

	public synchronized void saveRequest(String[] request) 
	{
		data.add(request[0]);
		data.add(request[1]);
		notifyAll(); // let threads waiting on this queue know that requests have been added to the buffer
		// importantly, this wakes up the TokenDistributor thread which is waiting on new requests
	}

	public void show() 
	{
		for (int i = 0; i < data.size(); i++)
		{
			System.out.print(" " + data.get(i) + " ");
		}
		System.out.println(" ");
	}

	public void add(Object o) 
	{
		data.add(o);
	}

	synchronized public Object get() 
	{
		Object o = null;

		if (!data.isEmpty()) 
		{
			o = data.get(0);
			data.remove(0);
		}
		return o;
	}

	public boolean isEmpty()
	{
      return data.isEmpty();
	}
}
