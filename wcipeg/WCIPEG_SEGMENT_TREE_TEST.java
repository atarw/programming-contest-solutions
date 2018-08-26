import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class WCIPEG_SEGMENT_TREE_TEST {

	static int[] tree, arr;

	public static void build (int pos, int l, int r) {
		if (l == r)
			tree[pos] = arr[l];
		else {
			int mid = (l + r) / 2;
			build (pos * 2 + 1, l, mid);
			build (pos * 2 + 2, mid + 1, r);
			tree[pos] = Math.min (tree[pos * 2 + 1], tree[pos * 2 + 2]);
		}
	}

	public static void update (int pos, int l, int r, int index) {
		if (l == r && l == index)
			tree[pos] = arr[index];
		else {
			int mid = (l + r) / 2;

			if (index <= mid)
				update (pos * 2 + 1, l, mid, index);
			else
				update (pos * 2 + 2, mid + 1, r, index);

			tree[pos] = Math.min (tree[pos * 2 + 1], tree[pos * 2 + 2]);
		}
	}

	public static int query (int pos, int l, int r, int ql, int qr) {
		if (l == r)
			return tree[pos];

		int mid = (l + r) / 2;

		if (qr <= mid)
			return query (pos * 2 + 1, l, mid, ql, qr);
		else if (ql > mid)
			return query (pos * 2 + 2, mid + 1, r, ql, qr);

		return Math.min (query (pos * 2 + 1, l, mid, ql, qr), query (pos * 2 + 2, mid + 1, r, ql, qr));
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();

		int height = (int) Math.ceil (Math.log (N) / Math.log (2));
		tree = new int[1 << (height + 1)];
		arr = new int[N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		build (0, 0, N - 1);

		for (int m = 0, index; m < M; ++m)
			if (in.cscan () == 'M') {
				index = in.iscan ();
				arr[index] = in.iscan ();
				update (0, 0, N - 1, index);
			}
			else
				out.println (query (0, 0, N - 1, in.iscan (), in.iscan ()));

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
				res = (res << 1) + (res << 3);
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