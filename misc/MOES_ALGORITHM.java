import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//for each query, count number of values in range [l,r] which are repeated at least 3 times
// offline only

public class MOES_ALGORITHM {

	static int block, curr_ans;
	static Map<Integer, Integer> map = new HashMap<Integer, Integer> ();

	public static void add (int v) {
		if (!map.containsKey (v))
			map.put (v, 1);
		else
			map.put (v, map.get (v) + 1);

		if (map.get (v) == 3)
			++curr_ans;
	}

	public static void remove (int v) {
		if (map.containsKey (v))
			if (map.get (v) == 1)
				map.remove (v);
			else {
				map.put (v, map.get (v) - 1);

				if (map.get (v) == 2)
					--curr_ans;
			}
	}

	public static void main (String[] t) throws IOException {
		PrintWriter out = new PrintWriter (System.out);

		int[] arr = {1, 2, 3, 1, 1, 2, 1, 2, 3, 1}; // size 10
		block = (int) Math.sqrt (arr.length);

		Query[] queries = {new Query (0, 3, 0), new Query (1, 7, 1), new Query (2, 8, 2), new Query (7, 8, 3), new
				Query (4, 8, 4), new Query (4, 4, 5), new Query (1, 2, 6)};
		Arrays.sort (queries); // {1, 2, 6} {0, 3, 0} {1, 7, 1} {2, 8, 2} {4, 4, 5} {4, 8, 4} {7, 8, 3}

		int[] ans = new int[queries.length];

		int currL = 0, currR = 0;

		for (Query q : queries) {
			// remove data from previous range
			while (currL < q.l) {
				remove (arr[currL]);
				++currL;
			}

			// add data from current range
			while (currL > q.l) {
				add (arr[currL - 1]);
				--currL;
			}

			while (currR <= q.r) {
				add (arr[currR]);
				++currR;
			}

			// remove data from previous range
			while (currR > q.r + 1) {
				remove (arr[currR - 1]);
				--currR;
			}

			// store answer
			ans[q.i] = curr_ans;
		}

		for (int q = 0; q < queries.length; ++q)
			out.println (ans[q]);

		out.close ();
	}

	private static class Query implements Comparable<Query> {

		int l, r, i; //l, r of query, i indicates original query order

		public Query (int l, int r, int i) {
			this.l = l;
			this.r = r;
			this.i = i;
		}

		public int compareTo (Query q) {
			if (this.l / block != q.l / block) // different blocks
				return Integer.compare (this.l / block, q.l / block);

			return Integer.compare (this.r, q.r); // same block, sort by r value
		}
	}
}