import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class CALCULATOR {

	public static int solve (int v1, int v2, String operator) {

		System.out.println (v2 + operator + v1);

		if (operator.equals ("+")) {
			return v1 + v2;
		}
		else if (operator.equals ("-")) {
			return v2 - v1;
		}
		else if (operator.equals ("*")) {
			return v1 * v2;
		}
		else {
			return v2 / v1;
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" +");
		Deque<String> operators = new LinkedList<String> ();
		Deque<Integer> operands = new LinkedList<Integer> ();
		Deque<String> brackets = new LinkedList<String> ();

		for (int i = 0; i < t.length; i++) {
			if (t[i].equals ("(")) {
				brackets.offer (t[i]);
			}
			else if (t[i].equals (")")) {
				brackets.poll ();

				if (!operands.isEmpty () && !operators.isEmpty ()) {
					operands.offer (solve (operands.pollLast (), operands.pollLast (), operators.pollLast ()));
				}
			}
			else if (t[i].equals ("*") || t[i].equals ("/") || t[i].equals ("+") || t[i].equals ("-")) {
				operators.offer (t[i]);
			}
			else {
				operands.offer (Integer.parseInt (t[i]));
			}
		}
		System.out.println (operands.poll ());
	}
}