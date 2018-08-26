import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class GFSSOC_17_S3_ZHANY_ZHANBIES {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), X = Integer.parseInt (t[1]), T = Integer.parseInt (t[2]);
		Queue<Zombie> zombies = new PriorityQueue<Zombie> ();

		for (int n = 0, i, j; n < N; ++n) {
			t = in.readLine ().split (" ");
			i = Integer.parseInt (t[0]);
			j = Integer.parseInt (t[1]);
			zombies.offer (new Zombie (i, j));
		}

		boolean live = false, killed;
		int pos = 1, bullets = T, moves = 0;

		while (!zombies.isEmpty ()) {
			Zombie curr = zombies.poll ();
			killed = false;

			if (bullets == 0) {
				bullets = T;
				++moves;
			}

			if (moves + Math.abs (pos - curr.r) >= curr.c) {
				break;
			}
			else {
				moves += 1 + Math.abs (pos - curr.r);
				--bullets;
				pos = curr.r;
				killed = true;
			}

			if (killed && zombies.isEmpty ())
				live = true;
		}

		out.println (live ? "You can do it, Ace!" : "Never lucky, Ace.");
		out.close ();
	}

	private static class Zombie implements Comparable<Zombie> {

		int r, c;

		public Zombie (int r, int c) {
			this.r = r;
			this.c = c;
		}

		public int compareTo (Zombie z) {
			if (this.c != z.c)
				return this.c - z.c;

			return this.r - z.r;
		}
	}
}