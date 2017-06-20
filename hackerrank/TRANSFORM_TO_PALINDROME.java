import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TRANSFORM_TO_PALINDROME {

	static int[] parent, rank, arr;
	static int[][] dp;

	public static int find (int u) {
		if (parent[u] != u)
			return parent[u] = find (parent[u]);

		return parent[u];
	}

	public static void union (int u, int v) {
		int r1 = find (u), r2 = find (v);

		if (r1 == r2)
			return;

		if (rank[r1] > rank[r2])
			parent[r2] = r1;
		else if (rank[r2] > rank[r1])
			parent[r1] = r2;
		else {
			parent[r2] = r1;
			++rank[r1];
		}
	}

	public static int lps (int l, int r) {
		if (dp[l][r] != -1)
			return dp[l][r];

		if (l == r)
			return dp[l][r] = 1;

		if (arr[l] == arr[r] && l == r - 1)
			return dp[l][r] = 2;

		if (arr[l] == arr[r])
			return dp[l][r] = lps (l + 1, r - 1) + 2;

		return dp[l][r] = Math.max (lps (l, r - 1), lps (l + 1, r));
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), K = Integer.parseInt (t[1]), M = Integer.parseInt (t[2]);

		parent = new int[N];
		rank = new int[N];

		for (int n = 0; n < N; ++n)
			parent[n] = n;

		for (int k = 0; k < K; ++k) {
			t = in.readLine ().split (" ");
			union (Integer.parseInt (t[0]) - 1, Integer.parseInt (t[1]) - 1);
		}

		t = in.readLine ().split (" ");

		arr = new int[M];
		dp = new int[M][M];

		for (int m = 0; m < M; ++m)
			Arrays.fill (dp[m], -1);

		for (int m = 0; m < M; ++m)
			arr[m] = find (Integer.parseInt (t[m]) - 1);

		System.out.print (lps (0, M - 1));
	}
}