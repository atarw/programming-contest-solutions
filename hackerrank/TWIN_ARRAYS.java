import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TWIN_ARRAYS {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		int N = Integer.parseInt (in.readLine ());
		Pair[] arr1 = new Pair[N], arr2 = new Pair[N];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; ++n)
			arr1[n] = new Pair (n, Integer.parseInt (t[n]));

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; ++n)
			arr2[n] = new Pair (n, Integer.parseInt (t[n]));

		Arrays.sort (arr1);
		Arrays.sort (arr2);

		int sum = 0;

		if (arr1[0].i != arr2[0].i)
			sum = arr1[0].v + arr2[0].v;
		else
			sum = Math.min (arr1[0].v + arr2[1].v, arr1[1].v + arr2[0].v);

		System.out.print (sum);
	}

	private static class Pair implements Comparable<Pair> {

		int i, v;

		public int compareTo (Pair p) {
			return Integer.compare (this.v, p.v);
		}

		public Pair (int i, int v) {
			this.i = i;
			this.v = v;
		}
	}
}