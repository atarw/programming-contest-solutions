import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class VMSS_15_P1_SEMPAI_HELP_ME {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());

		int min = Integer.MAX_VALUE;
		int x = 0, y = 0;

		for (int i = 1; i < N; i++) {
			if (N % i == 0 && Math.abs (N / i - i) <= min) {
				min = Math.abs (N / i - i);
				x = i;
				y = N / i;
			}
		}

		System.out.print (y * 2 + x * 2);
	}
}