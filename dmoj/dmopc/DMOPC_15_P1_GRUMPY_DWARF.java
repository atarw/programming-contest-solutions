import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMOPC_15_P1_GRUMPY_DWARF {

	public static long getSwords (long bars, long rate) {
		long swords = 0;
		long total = 0;

		while (bars > 0) {
			{
				total += bars;
				//System.out.println ("MADE " + bars + " SWORDS, NOW 0 BARS ---- " + total);
				swords += bars;
				bars = 0;

				bars = (swords / rate);
				// System.out.print ("TRADED " + swords + " SWORDS FOR " + bars + " BARS");
				swords = swords - bars * rate;
				// System.out.println (", HAVE " + swords + " LEFT  ----- " + total);
			}
		}

		return total;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		long N = Long.parseLong (in.readLine ());
		long K = Long.parseLong (in.readLine ());

		System.out.println (getSwords (N, K));
	}
}