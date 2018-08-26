import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class DMOPC_15_P5_SCRIBE {

	public static long countD (int d, int lim) {
		long ori = lim, curr = 0;
		long count = 0;

		for (long i = 0, pow = 1; lim > 0; ++i, pow *= 10) {
			curr = lim % 10;
			lim /= 10;

			count += curr * (pow * i) / 10;

			if (curr > d)
				count += pow;
			else if (curr == d)
				count += ori % pow + 1;
		}

		return count;
	}

	public static long countZ (int lim) {
		long count = 0;

		for (long pow = 1, q = lim / pow, r = lim % pow, q2 = q / 10; q2 > 0; pow *= 10, q = lim / pow, r = lim % pow,
				q2 = q / 10) {
			q %= 10;

			if (q == 0)
				count += (q2 - 1) * pow + r + 1;
			else
				count += q2 * pow;
		}

		return count;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int T = Integer.parseInt (in.readLine ());

		while (T-- > 0) {
			t = in.readLine ().split (" ");
			int x = Integer.parseInt (t[0]) - 1, y = Integer.parseInt (t[1]);

			out.print (countZ (y) - countZ (x) + " ");

			for (int d = 1; d < 10; ++d)
				out.print (countD (d, y) - countD (d, x) + " ");

			out.println ();
		}

		out.close ();
	}
}