import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class FHC_15_R1_AUTOCOMPLETE {

	static int[][] trie;
	static int insertionPoint;

	//returns longest prefix already in array
	public static int insert (String word) {
		int curr = 1, pos, length = 0;
		boolean exists = true;//previously exists

		for (int i = 0; i < word.length (); ++i) {
			pos = word.charAt (i) - 'a';

			if (trie[curr][pos] == 0) {//no child node at this position
				trie[curr][pos] = ++insertionPoint;
				exists = false;
			}
			else {
				++length;
			}

			curr = trie[curr][pos];//set current node to child node
		}

		return length + (exists ? 0 : 1);
	}

	public static void main (String[] words) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int T = in.iscan ();
		int N, total, size;

		for (int t = 1; t <= T; ++t) {
			insertionPoint = 1;

			N = in.iscan ();
			total = 0;
			size = 0;
			words = new String[N];

			for (int n = 0; n < N; ++n) {
				words[n] = in.sscan ();
				size += words[n].length ();
			}

			trie = new int[size + 1][26];
			System.gc ();

			for (int n = 0; n < N; ++n) {
				total += insert (words[n]);
			}

			out.print ("Case #");
			out.print (t);
			out.print (": ");
			out.println (total);
		}

		//trie = null; System.gc ();
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
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);

				if (numChars <= 0) return -1;
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
				res *= 10;
				res += c - '0';

				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		public String sscan () throws IOException {
			int c = cscan ();
			while (space (c)) c = cscan ();

			StringBuilder res = new StringBuilder ();

			do {
				res.appendCodePoint (c);
				c = cscan ();
			}
			while (!space (c));

			return res.toString ();
		}

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}