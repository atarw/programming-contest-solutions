import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TSOC_15_P5_BEBILITHS {

	public static int S;

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		S = Integer.parseInt (in.readLine ());
		int N = Integer.parseInt (in.readLine ());
		Bebilith[] arr = new Bebilith[N];
		String[] t;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			arr[n] = new Bebilith (n + 1, Integer.parseInt (t[0]), Integer.parseInt (t[1]), Integer.parseInt (t[2]));
		}

		Arrays.sort (arr);

		int Q = Integer.parseInt (in.readLine ());

		for (int q = 0; q < Q; q++) {
			System.out.println (arr[Integer.parseInt (in.readLine ()) - 1].J);
		}
	}
}

class Bebilith implements Comparable <Bebilith> {

	int J, B, D, C;

	public Bebilith (int J, int B, int D, int C) {
		this.J = J;
		this.B = B;
		this.D = D;
		this.C = C;
	}

	public int compareTo (Bebilith b) {
		if (B == b.B) {
			if (B >= TSOC_15_P5_BEBILITHS.S) {
				return b.C - C;
			}
			else if (B < TSOC_15_P5_BEBILITHS.S) {
				return b.D - D;
			}
			else {
				return b.J - J;
			}
		}
		else {
			return b.B - B;
		}
	}
}