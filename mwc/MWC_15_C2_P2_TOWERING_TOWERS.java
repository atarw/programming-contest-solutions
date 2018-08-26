import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// atharva washimkar
// August 11, 2018

public class MWC_15_C2_P2_TOWERING_TOWERS {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		t = in.readLine ().split (" ");
		int[] height = new int[N], sight = new int[N];

		for (int n = 0; n < N; n++) {
			height[n] = Integer.parseInt (t[n]);
		}

		Deque<Integer> queue = new ArrayDeque<Integer> (N);
		queue.push (0);

		for (int n = 1; n < N; n++) {
			while (!queue.isEmpty () && height[queue.peek ()] <= height[n]) {
				queue.pop ();
			}

			sight[n] = queue.isEmpty () ? n : n - queue.peek ();
			queue.push (n);
		}

		for (int n = 0; n < N; n++) {
			System.out.print (sight[n] + " ");
		}
	}
}