import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CCC_16_S5_CIRCLE_OF_LIFE {

	static String pad (String s, int N) {
		if (s.length () == N)
			return s;
		while (s.length () != N)
			s = '0' + s;
		return s;
	}

	public static void main (String[] args) throws IOException {
		int N;
		long T;
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] temp = in.readLine ().split (" ");
		N = Integer.parseInt (temp[0]);
		T = Long.parseLong (temp[1]);
		Map<String, Integer> index = new HashMap<String, Integer> ();
		ArrayList<String> strips = new ArrayList<String> ();
		String s;
		BigInteger a = new BigInteger (s = in.readLine (), 2);
		strips.add (s);
		BigInteger b;
		int cycle = 0, at = 0;
		while (true) {
			b = a.shiftLeft (1).xor (a.testBit (N - 1) ? BigInteger.ONE.shiftLeft (N) : BigInteger.ZERO).xor (a.testBit (N - 1) ? BigInteger.ONE : BigInteger.ZERO).xor (a.shiftRight (1).xor (a.testBit (0) ? BigInteger.ONE.shiftLeft (N - 1) : BigInteger.ZERO));
			at++;
			if (index.containsKey (s = b.toString (16))) {
				cycle = at - index.get (s);
				T -= index.get (s);
				T %= cycle;
				T += index.get (s);
				System.out.println (pad (strips.get ((int) T), N));
				return;
			}
			else {
				index.put (s, at);
			}
			strips.add (b.toString (2));
			if (at == T) {
				System.out.println (pad (strips.get (at), N));
				return;
			}
			a = b;
		}
	}
}