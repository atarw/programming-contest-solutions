import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P2_TILES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] tokens = (in.readLine ()).split (" ");

		int X = Integer.parseInt (tokens[0]), Y = Integer.parseInt (tokens[1]), T = Integer.parseInt (in.readLine ());

		System.out.println ((X / T) * (Y / T));
	}
}