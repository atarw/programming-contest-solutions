//TLES - 80/100, BUT IDENTICAL C++ SOLUTION PASSES ;_;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class STARDUST_SNOW {

	static int[][][][] cache = new int[52][51][51][51];//time, pos, temp, cap -> max
	static int[][][] flakes = new int[51][51][2];//[horizontal pos /C/], and [time when pos 1 reached] [0 = T, 1 = V]
	static int R, C, B, K, M, S, T;//T = max time

	public static int solve (int time, int pos, int temp, int cap) {
		if (cache[time][pos][temp][cap] != 0 || time == T + 1 || cap == K || temp >= B) {
			return cache[time][pos][temp][cap];
		}

		//has option to take
		if (flakes[pos][time][1] != 0 && temp + flakes[pos][time][0] < B && cap + 1 <= K) {
			for (int move = M; move >= 0; --move) {
				if (pos - move >= 0) {
					cache[time][pos][temp][cap] = Math.max (cache[time][pos][temp][cap], flakes[pos][time][1] + solve
							(time + 1, pos - move, temp + flakes[pos][time][0], cap + 1));
				}

				if (pos + move <= C) {
					cache[time][pos][temp][cap] = Math.max (cache[time][pos][temp][cap], flakes[pos][time][1] + solve
							(time + 1, pos + move, temp + flakes[pos][time][0], cap + 1));
				}
			}
		}

		//cannot take at all
		for (int move = M; move >= 0; --move) {
			if (pos - move >= 0) {
				cache[time][pos][temp][cap] = Math.max (cache[time][pos][temp][cap], solve (time + 1, pos - move,
				                                                                            temp, cap));
			}

			if (pos + move <= C) {
				cache[time][pos][temp][cap] = Math.max (cache[time][pos][temp][cap], solve (time + 1, pos + move,
				                                                                            temp, cap));
			}
		}

		return cache[time][pos][temp][cap];
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		R = in.iscan ();
		C = in.iscan ();
		S = in.iscan ();
		B = in.iscan ();
		K = in.iscan ();
		M = in.iscan ();

		int T_i, V_i, C_i, R_i;

		for (int s = 0; s < S; ++s) {
			T_i = in.iscan ();
			V_i = in.iscan ();
			C_i = in.iscan ();
			R_i = in.iscan ();

			flakes[C_i][R_i][0] = T_i;
			flakes[C_i][R_i][1] = V_i;
			T = Math.max (T, R_i);
		}

		out.print (solve (0, 1, 0, 0));
		out.close ();
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public int cscan () throws IOException {
			//if (numChars == -1) throw new InputMismatchException ();

			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);

				//if (numChars <= 0) return -1;
			}

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			int res = 0;

			do {
				//if (c < '0' || c > '9') throw new InputMismatchException ();
				res *= 10;
				res += c - '0';

				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}