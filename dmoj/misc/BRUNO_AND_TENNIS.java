import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// atharva washimkar
// August 11, 2018

public class BRUNO_AND_TENNIS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		Deque<String> stack = new ArrayDeque<String> ();
		String result = "Not enough information";

		for (int i = 0; i < N / 2; i++) {
			stack.add (in.readLine ());
			stack.add (in.readLine ());

			if (stack.getFirst ().equals ("lob") || stack.getLast ().equals ("lob")) {
				if (stack.getFirst ().equals (stack.getLast ())) {
					result = "Possible Bruno";
				}
				else {
					result = "BruNO";
					break;
				}
			}
			stack.clear ();
		}
		System.out.println (result);
	}
}