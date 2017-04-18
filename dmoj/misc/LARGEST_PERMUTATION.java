import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LARGEST_PERMUTATION {

	static int[] arr;//stores actual numbers

	public static void permute (int K, int max) {
		int to = 0;

		for (int n = 0; n < arr.length && K > 0; n++) {
			if (arr[n] == max) {
				if (n != to) {
					arr[n] = arr[to];
					arr[to] = max;

					K--;
				}
				max--;
				to++;
				n = -1;
			}
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), K = Integer.parseInt (t[1]), max = -1;
		arr = new int[N];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt (t[n]);
			max = Math.max (arr[n], max);
		}

		permute (K, max);

		for (int n : arr) {
			System.out.print (n + " ");
		}
	}
}