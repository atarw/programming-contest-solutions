import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CCC_16_S2_TANDEM_BICYCLE {

	static int[] arr1, arr2;

	public static int min () {
		int sum = 0;

		for (int i = 0; i < arr1.length; i++) {
			sum += Math.max (arr1[i], arr2[i]);
		}

		return sum;
	}

	public static int max () {
		int sum = 0;

		for (int i = 0; i < arr1.length; i++) {
			sum += Math.max (arr1[i], arr2[arr1.length - 1 - i]);
		}

		return sum;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int T = Integer.parseInt (in.readLine ());
		int N = Integer.parseInt (in.readLine ());

		arr1 = new int[N];
		arr2 = new int[N];

		String[] t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			arr1[n] = Integer.parseInt (t[n]);
		}

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			arr2[n] = Integer.parseInt (t[n]);
		}

		Arrays.sort (arr1);
		Arrays.sort (arr2);

		if (T == 1) {
			System.out.println (min ());
		}
		else {
			System.out.println (max ());
		}
	}
}