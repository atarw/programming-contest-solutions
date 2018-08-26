import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class LONGEST_INCREASING_SUBSEQUENCE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int[] arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
		int[] dp = new int[arr.length]; // returns length of LIS ending at index n

		for (int i = 0; i < arr.length; ++i)
			dp[i] = 1; // since the LIS can just be the one element at arr [i]

		for (int i = 1; i < arr.length; ++i)
			for (int j = 0; j < i; ++j)
				if (arr[j] < arr[i]) // LIS ending at i is equal to LIS ending at some earlier index j, plus the
					// element at i (as long as the element at i is greater than the element at j)
					dp[i] = Math.max (dp[i], dp[j] + 1);

		int max = 0;

		for (int i = 0; i < arr.length; ++i)
			max = Math.max (max, dp[i]);

		out.print (max);
		out.close ();
	}
}