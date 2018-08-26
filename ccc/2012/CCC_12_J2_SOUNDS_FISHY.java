import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_12_J2_SOUNDS_FISHY {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ()), C = Integer.parseInt (in.readLine ()), D = Integer.parseInt (in.readLine ());

		if (A < B && B < C && C < D) {
			System.out.println ("Fish Rising");
		}
		else if (A > B && B > C && C > D) {
			System.out.println ("Fish Diving");
		}
		else if (A == B && B == C && C == D) {
			System.out.println ("Fish At Constant Depth");
		}
		else {
			System.out.println ("No Fish");
		}
	}
}