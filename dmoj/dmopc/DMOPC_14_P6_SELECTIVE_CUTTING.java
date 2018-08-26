import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class DMOPC_14_P6_SELECTIVE_CUTTING {

	static Query[] qs;
	static long[] ans;
	static Node[] tree;

	public static int l (int pos) {
		return 2 * pos + 1;
	}

	public static int r (int pos) {
		return 2 * pos + 2;
	}

	public static void build (int pos, int l, int r) {
		tree[pos].l = l;
		tree[pos].r = r;

		if (l == r) {
			tree[pos].sum = 0;
		}
		else {
			int mid = (l + r) / 2;

			build (l (pos), l, mid);
			build (r (pos), mid + 1, r);

			tree[pos].sum = tree[l (pos)].sum + tree[r (pos)].sum;
		}
	}

	public static void update (int pos, int a_pos, int x) {
		if (tree[pos].l == tree[pos].r && tree[pos].l == a_pos) {
			tree[pos].sum = x;
		}
		else {
			int mid = (tree[pos].l + tree[pos].r) / 2;

			if (a_pos <= mid) {
				update (l (pos), a_pos, x);
			}
			else {
				update (r (pos), a_pos, x);
			}

			tree[pos].sum = tree[l (pos)].sum + tree[r (pos)].sum;
		}
	}

	public static long query (int pos, int l, int r) {
		if (tree[pos].l == l && tree[pos].r == r) {
			return tree[pos].sum;
		}

		int mid = (tree[pos].l + tree[pos].r) / 2;

		if (r <= mid) {
			return query (l (pos), l, r);
		}
		else if (l > mid) {
			return query (r (pos), l, r);
		}
		else {
			return query (l (pos), l, mid) + query (r (pos), mid + 1, r);
		}
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		Tree[] arr = new Tree[N];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; ++n) {
			arr[n] = new Tree (Integer.parseInt (t[n]), n);
		}

		Arrays.sort (arr);

		int Q = Integer.parseInt (in.readLine ());
		qs = new Query[Q];
		ans = new long[Q];

		for (int q = 0; q < Q; ++q) {
			t = in.readLine ().split (" ");
			qs[q] = new Query (Integer.parseInt (t[0]), Integer.parseInt (t[1]), Integer.parseInt (t[2]), q);
		}

		Arrays.sort (qs);

		int height = (int) Math.ceil (Math.log (N) / Math.log (2)) + 2;
		int SIZE = 1 << height;

		tree = new Node[SIZE];

		for (int s = 0; s < SIZE; ++s) {
			tree[s] = new Node ();
		}

		build (0, 0, N - 1);
		int index = 0;

		for (int q = 0; q < Q; ++q) {
			while (true) {
				if (index < N && arr[index].m >= qs[q].m) {
					update (0, arr[index].ord, arr[index].m);
					++index;
				}
				else {
					break;
				}
			}

			ans[qs[q].ord] = query (0, qs[q].a, qs[q].b);
		}

		for (int q = 0; q < Q; ++q) {
			out.println (ans[q]);
		}

		out.close ();
	}

	private static class Tree implements Comparable<Tree> {

		int m, ord;

		public Tree (int m, int ord) {
			this.m = m;
			this.ord = ord;
		}

		public int compareTo (Tree t) {
			return t.m - this.m;
		}
	}

	private static class Query implements Comparable<Query> {

		int a, b, m, ord;

		public Query (int a, int b, int m, int ord) {
			this.a = a;
			this.b = b;
			this.m = m;
			this.ord = ord;
		}

		public int compareTo (Query q) {
			return q.m - this.m;
		}
	}

	private static class Node {

		int l, r;
		long sum;
	}
}