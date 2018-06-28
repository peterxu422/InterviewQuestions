/*
You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:

record(order_id): adds the order_id to the log
get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
*/
import java.lang.*;
public class LastOrderIdsInLog
{
	private String[] queue;
	private int curIdx = 0;

	public LastOrderIdsInLog(int n)
	{
		queue = new String[n];
	}

	public void record(String orderId)
	{
		queue[curIdx] = orderId;

		curIdx++;
		if (curIdx == queue.length)
		{
			curIdx = 0;
		}
	}

	public String get_last(int i)
	{
		int diff = curIdx - i;
		int lastIthIdx = diff < 0 ? queue.length + diff : diff;
		return queue[lastIthIdx];
	}

	public static void main(String[] args)
	{
		LastOrderIdsInLog lastOrderIds = new LastOrderIdsInLog(5);
		lastOrderIds.record("abc123");
		lastOrderIds.record("345");
		System.out.println(lastOrderIds.get_last(1)); // 345
		System.out.println(lastOrderIds.get_last(2)); // abc123
		System.out.println(lastOrderIds.get_last(4)); // null

		lastOrderIds.record("6");
		System.out.println(lastOrderIds.get_last(1)); // 6
		lastOrderIds.record("7");
		lastOrderIds.record("8");
		System.out.println(lastOrderIds.get_last(5)); // abc123

		lastOrderIds.record("9");
		lastOrderIds.record("10");
		lastOrderIds.record("11");
		System.out.println(lastOrderIds.get_last(1)); // 11
		System.out.println(lastOrderIds.get_last(2)); // 10
		System.out.println(lastOrderIds.get_last(3)); // 9
		System.out.println(lastOrderIds.get_last(4)); // 8
		System.out.println(lastOrderIds.get_last(5)); // 7
	}
}