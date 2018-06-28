/*
Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.

Follow-up: Can you do this in O(N) time and constant space?

Assumptions:
- Do numbers themselves count or does it have to be a sum? No, it should be a sum.
*/
import java.util.*;

public class LargestNonAdjacentSum
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println(maxNonAdjSum(new int[]{2, 4, 6, 2, 5})); // 13
			System.out.println(maxNonAdjSum(new int[]{5, 1, 1, 5})); // 10
			System.out.println(maxNonAdjSum(new int[]{2, 1})); // 2
			System.out.println(maxNonAdjSum(new int[]{2, -1, 4, 7, -3})); // 9
			System.out.println(maxNonAdjSum(new int[]{-2, -1, 4, 7, -3})); // 6 (or 7, if count single numbers)
			System.out.println(maxNonAdjSum(new int[]{-1, -3, -4, -5})); // -1 (or -1, if count single numbers)
			System.out.println(maxNonAdjSum(new int[]{-1, -3, 4, -5})); // -3 (or 4, if count single numbers)
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static int maxNonAdjSum(int[] a) throws Exception
	{
		if (a == null || a.length == 0)
		{
			throw new Exception("Cannot have empty array");
		}

		int maxNonAdjSum = Integer.MIN_VALUE;
		int maxNonAdjPrev = 0;
		int maxNonAdjPrev2 = 0;
		int maxNonAdjPrev3 = 0;

		for (int i = 0; i < a.length; i++)
		{
			if (a.length == 1)
			{
				maxNonAdjSum = a[0];
			}
			else if (a.length == 2)
			{
				maxNonAdjSum = Math.max(a[0], a[1]);
				maxNonAdjPrev = a[1];
				maxNonAdjPrev2 = a[0];
			}
			else
			{
				// int maxNonAdjAtCur = Math.max(a[i], Math.max(a[i] + maxNonAdjPrev2, a[i] + maxNonAdjPrev3)); // If considering only the number itself
				int maxNonAdjAtCur = i == 2 ? (a[i] + maxNonAdjPrev2) : Math.max(a[i] + maxNonAdjPrev2, a[i] + maxNonAdjPrev3);
				maxNonAdjSum = Math.max(maxNonAdjSum, maxNonAdjAtCur);
				maxNonAdjPrev3 = maxNonAdjPrev2;
				maxNonAdjPrev2 = maxNonAdjPrev;
				maxNonAdjPrev = maxNonAdjAtCur;
			}
		}

		return maxNonAdjSum;
	}
}