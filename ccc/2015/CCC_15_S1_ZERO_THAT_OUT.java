import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class CCC_15_S1_ZERO_THAT_OUT {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int K = Integer.parseInt (in.readLine ());
		int sum = 0;

		Deque <Integer> stack = new ArrayDeque <Integer> ();
		Iterator <Integer> iterator;

		for (int i = 0; i < K; i++) {
			int x = Integer.parseInt (in.readLine ());

			if (x != 0) {
				stack.add (x);
			}
			else {
				stack.removeLast ();
			}
		}

		iterator = stack.iterator ();

		while (iterator.hasNext ()) {
			sum += iterator.next ();
		}

		System.out.println (sum);
	}
}