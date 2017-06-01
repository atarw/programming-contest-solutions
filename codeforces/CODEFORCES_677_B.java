import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class CODEFORCES_677_B {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), H = Integer.parseInt (t[1]), K = Integer.parseInt (t[2]);
		Queue<Integer> q = new ArrayDeque<Integer> (N);

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			q.offer (Integer.parseInt (t[n]));
		}

		int sec = 0;
		int cap = 0;
		int mult = 0;

		while (!q.isEmpty () || cap != 0) {
			System.out.println (q + " " + cap + " " + sec + " " + mult);

			while (!q.isEmpty () && cap + q.peek () <= H) {
				cap += q.poll ();
			}

			if (!q.isEmpty ()) {
				mult = q.peek () + K - q.peek () % K;//smallest multiple of K which is greater than q.peek ()
				//mult = (H - cap) - (H - cap) % K;//largest multiple of K which is smaller than H - cap

				sec += mult / K;
				cap = Math.max (0, cap - mult);
			}
			else {
				sec += Math.ceil (cap * 1.0 / K);
				cap = 0;
			}

			//cap = Math.max (0, cap - K);
			//sec++;
		}

		System.out.print (sec);
	}
}