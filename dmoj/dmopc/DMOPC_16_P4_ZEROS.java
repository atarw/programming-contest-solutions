import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class DMOPC_16_P4_ZEROS {

	public static long upper_bound (long x) {
		if (x < 0)
			return 1;

		long low = 0L, high = Long.MAX_VALUE, mid = -1L;

		while (low < high) {
			mid = (low + high) / 2L;

			if (trailing_zeros (mid) > x)
				high = mid;
			else
				low = mid + 1L;
		}

		return low;
	}

	public static long trailing_zeros (long n) {
		long cnt = 0L;

		for (long i = 5L; n / i >= 1L; i *= 5L)
			cnt += n / i;

		return cnt;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		long A = Integer.parseInt (t[0]), B = Integer.parseInt (t[1]);
		long hi = upper_bound (B), lo = upper_bound (A - 1);

		out.println (hi - lo);
		out.close ();
	}
}