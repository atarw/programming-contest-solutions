import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class SANDWICH {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String input = in.readLine ();
		Deque<Integer> deque = new LinkedList<Integer> ();

		for (int i = 0; i < N; i++) {
			if (input.charAt (i) == '0') {
				deque.addLast (i + 1);
			}
			else {
				deque.addFirst (i + 1);
			}
		}

		for (Integer i : deque) {
			System.out.println (i);
		}
	}
}