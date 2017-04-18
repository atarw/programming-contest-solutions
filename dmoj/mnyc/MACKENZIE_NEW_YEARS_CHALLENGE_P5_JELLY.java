import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MACKENZIE_NEW_YEARS_CHALLENGE_P5_JELLY {

	static int[][][] maze;
	static int[][][] steps;
	static P3D j;

	public static int solve () {
		Queue <P3D> queue = new PriorityQueue <P3D> ();
		List <P3D> n;
		P3D curr = j;
		queue.offer (j);

		int min = Integer.MAX_VALUE;

		while (!queue.isEmpty ()) {
			curr = queue.poll ();

			if (curr.X == 0 || curr.Y == 0 || curr.Z == 0 || curr.X == maze[0][0].length - 1 || curr.Y == maze[0]
					.length - 1 || curr.Z == maze.length - 1) {
				min = Math.min (min, steps[curr.Z][curr.Y][curr.X]);
				break;
			}

			n = neighbours (curr);

			for (P3D u : n) {
				if (steps[u.Z][u.Y][u.X] > steps[curr.Z][curr.Y][curr.X] + maze[u.Z][u.Y][u.X] || !u.equals (j) &&
						steps[u.Z][u.Y][u.X] == 0) {
					steps[u.Z][u.Y][u.X] = steps[curr.Z][curr.Y][curr.X] + maze[u.Z][u.Y][u.X];
				}

				if (steps[u.Z][u.Y][u.X] < min) {
					queue.offer (u);
				}
			}
		}

		return min;
	}

	public static List <P3D> neighbours (P3D curr) {
		List <P3D> neighbours = new ArrayList <P3D> (6);

		if (valid (curr.X + 1, curr.Y, curr.Z)) {
			neighbours.add (new P3D (curr.X + 1, curr.Y, curr.Z));
		}
		if (valid (curr.X - 1, curr.Y, curr.Z)) {
			neighbours.add (new P3D (curr.X - 1, curr.Y, curr.Z));
		}
		if (valid (curr.X, curr.Y + 1, curr.Z)) {
			neighbours.add (new P3D (curr.X, curr.Y + 1, curr.Z));
		}
		if (valid (curr.X, curr.Y - 1, curr.Z)) {
			neighbours.add (new P3D (curr.X, curr.Y - 1, curr.Z));
		}
		if (valid (curr.X, curr.Y, curr.Z + 1)) {
			neighbours.add (new P3D (curr.X, curr.Y, curr.Z + 1));
		}
		if (valid (curr.X, curr.Y, curr.Z - 1)) {
			neighbours.add (new P3D (curr.X, curr.Y, curr.Z - 1));
		}
		return neighbours;
	}

	public static boolean valid (int X, int Y, int Z) {
		return !(X < 0 || Y < 0 || Z < 0 || Z >= maze.length || Y >= maze[0].length || X >= maze[0][0].length ||
				steps[Z][Y][X] != 0 || j.X == X && j.Y == Y && j.Z == Z);
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		String ln;

		int X = Integer.parseInt (t[0]), Y = Integer.parseInt (t[1]), Z = Integer.parseInt (t[2]);
		maze = new int[Z][Y][X];
		steps = new int[Z][Y][X];

		for (int z = 0; z < Z; z++) {
			for (int y = 0; y < Y; y++) {
				ln = in.readLine ();

				for (int x = 0; x < X; x++) {
					if (ln.charAt (x) == 'J') {
						j = new P3D (x, y, z);
					}
					else {
						maze[z][y][x] = Character.getNumericValue (ln.charAt (x));
					}
				}
			}
		}
		System.out.println (solve ());
	}

	private static class P3D implements Comparable <P3D> {

		int X, Y, Z;

		public P3D (int X, int Y, int Z) {
			this.X = X;
			this.Y = Y;
			this.Z = Z;
		}

		public boolean equals (Object o) {
			P3D p = ((P3D) o);
			return X == p.X && Y == p.Y && Z == p.Z;
		}

		public String toString () {
			return "(" + X + ", " + Y + ", " + Z + ") " + steps[Z][Y][X];
		}

		public int compareTo (P3D p) {
			return steps[Z][Y][X] - steps[p.Z][p.Y][p.X];
		}
	}
}