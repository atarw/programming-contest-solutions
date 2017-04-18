import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TLE_16_P2_HEAVY_LIGHT_DECOMPOSITION {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		int[] arr = new int[N];

		for (int n = 0; n < N; ++n)
			arr[n] = Integer.parseInt (in.readLine ());

		Arrays.sort (arr);

		int sum = 0;

		for (int a : arr)
			sum += a;

		double mean = sum * 1.0 / N;

		double median = 0;
		int mid = N / 2;

		if (N % 2 == 0) {
			median = (arr[mid] + arr[mid - 1]) / 2.0;
		}
		else {
			median = arr[mid];
		}

		double mode = 0;

		Map <Integer, Integer> map = new HashMap <Integer, Integer> ();

		for (int n = 0; n < N; ++n) {
			if (map.containsKey (arr[n])) {
				map.put (arr[n], map.get (arr[n]) + 1);
			}
			else {
				map.put (arr[n], 1);
			}
		}

		int max_freq = 0, max_occ = 0, elem = 0;

		for (int key : map.keySet ()) {
			if (max_occ < map.get (key)) {
				max_occ = map.get (key);
				elem = key;
				max_freq = 1;
			}
			else if (max_occ == map.get (key)) {
				max_freq++;
			}
		}

		if (max_freq > 1) {
			int _sum = 0;
			int count = 0;

			for (int i : map.keySet ()) {
				if (map.get (i) == max_occ) {
					_sum += i * map.get (i);
					count += map.get (i);
				}
			}

			mode = _sum * 1.0 / count;
		}
		else
			mode = elem;

		int pos1 = 0, pos2 = 0, pos3 = 0;

		for (int n = 0; n < N; ++n) {
			if (arr[n] <= mean)
				pos1++;

			if (arr[n] <= median)
				pos2++;

			if (arr[n] <= mode)
				pos3++;
		}

		//out.println (mean + " " + median + " " + mode);
		out.println (Math.min (pos1, Math.min (pos2, pos3)));
		out.close ();
	}
}