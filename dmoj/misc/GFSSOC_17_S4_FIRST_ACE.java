import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class GFSSOC_17_S4_FIRST_ACE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ()), sum = 10000;
		Person[] p = new Person[N];

		for (int n = 0; n < N; ++n) {
			t = in.readLine ().split (" ");
			p[n] = new Person (Integer.parseInt (t[0]), Integer.parseInt (t[1]), Integer.parseInt (t[2]));
			//sum += p [n].s;
		}

		Arrays.sort (p);

		int[] top = new int[sum + 1];
		int[] bot = new int[sum + 1];

		for (int n = N - 1; n >= 0; --n) {
			for (int tt = sum - 1; tt >= 0; --tt) {
				bot[tt] = top[tt];

				if (p[n].t >= tt + p[n].s)
					bot[tt] = Math.max (bot[tt], p[n].v + top[tt + p[n].s]);
			}

			for (int s = 0; s <= sum; ++s) {
				top[s] = bot[s];
				bot[s] = 0;
			}
		}

		out.println (top[0]);
		out.close ();
	}

	private static class Person implements Comparable <Person> {

		int v, s, t;

		public Person (int v, int s, int t) {
			this.v = v;
			this.s = s;
			this.t = t;
		}

		public int compareTo (Person p) {
			return this.t - p.t;
		}
	}
}