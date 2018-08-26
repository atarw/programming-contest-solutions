import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ICPC_05_A_ACM {

	static String acronym;
	static String[] tok;
	static Set<String> set;
	static int[][][][] dp;

	public static int solve (int n, int t, int k, int b) {
		if (dp[n][t][k][b] != -1)
			return dp[n][t][k][b];

		if (n == acronym.length ())
			return dp[n][t][k][b] = (t == tok.length || t == tok.length - 1 && b == 1) ? 1 : 0;

		if (t == tok.length)
			return dp[n][t][k][b] = 0;

		dp[n][t][k][b] = 0;

		for (int i = k; i < tok[t].length (); ++i)
			if (Character.toLowerCase (acronym.charAt (n)) == Character.toLowerCase (tok[t].charAt (i)))
				dp[n][t][k][b] += solve (n + 1, t + 1, 0, 0) + solve (n + 1, t, i + 1, 1);

		return dp[n][t][k][b];
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		while (true) {
			int N = Integer.parseInt (in.readLine ());

			if (N == 0)
				break;

			set = new HashSet<String> ();

			for (int n = 0; n < N; ++n)
				set.add (in.readLine ());

			while (true) {
				String ln = in.readLine ();

				if (ln.equals ("LAST CASE"))
					break;

				t = ln.split (" ");
				acronym = t[0];

				List<String> list = new ArrayList<String> ();

				for (int i = 1; i < t.length; ++i)
					if (!set.contains (t[i]))
						list.add (t[i]);

				tok = new String[list.size ()];

				for (int i = 0; i < tok.length; ++i)
					tok[i] = list.get (i);

				dp = new int[acronym.length () + 1][tok.length + 1][151][2];

				for (int i = 0; i < dp.length; ++i)
					for (int j = 0; j < dp[i].length; ++j)
						for (int k = 0; k < dp[i][j].length; ++k)
							for (int l = 0; l < dp[i][j][k].length; ++l)
								dp[i][j][k][l] = -1;

				if (solve (0, 0, 0, 0) == 0)
					out.println (acronym + " is not a valid abbreviation");
				else
					out.println (acronym + " can be formed in " + (dp[0][0][0][0] / 2) + " ways");
			}
		}

		out.close ();
	}
}