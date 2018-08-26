import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BALANCED_PARENTHESES {

	public static boolean balanced (String input) {
		char[] t = input.toCharArray ();
		Deque<Character> queue = new LinkedList<Character> ();

		for (int i = 0; i < t.length; i++) {
			if (t[i] == '(') {
				queue.offer (')');
			}
			else if (t[i] == '[') {
				queue.offer (']');
			}
			else if (t[i] == '{') {
				queue.offer ('}');
			}
			else if (queue.pollLast () != t[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		System.out.println (balanced (in.readLine ()));
	}
}