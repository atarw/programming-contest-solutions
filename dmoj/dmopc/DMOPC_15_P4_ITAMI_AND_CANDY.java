import java.util.*;
import java.io.*;

public class DMOPC_15_P4_ITAMI_AND_CANDY {
	
	public static Queue <Integer> primes (int N) {
		boolean [] sieve = new boolean [N + 1];
		Queue <Integer> primes = new LinkedList <Integer> ();
		
		primes.offer (2);
		
		for (int i = 2; i <= N; i++) {
			if (!sieve [i] && i % 2 != 0) {
				for (int x = i, y = 2; x <= N; x = i * y, y++) {
					sieve [x] = true;
				}
				primes.offer (i);
			}
		}
		return primes;
	}
	
	public static int getPossibilities (Queue <Integer> primes, int R, int C) {
		
		int possibilities = 0;
		int diff = 0;
		int q, r;
		
		while (!primes.isEmpty ()) {
			diff = C - primes.poll ();
			
			if (diff == 0) {
				possibilities++;
			}
			else if (diff > 0) {
				q = diff / R;
				r = diff % R;
				
				possibilities += 2 * q + 2;
				
				if (r == 0) {
					possibilities--;
				}
			}
		}
		return possibilities;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String [] t = in.readLine ().split (" ");
		int C = Integer.parseInt (t [0]);
		
		System.out.println (getPossibilities (primes (C), Integer.parseInt (t [1]), C));
	}
}