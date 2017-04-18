import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CCC_96_S4_WHEN_IN_ROME {

	static Map <Character, Integer> map = new HashMap <Character, Integer> ();
	static NavigableMap <Integer, Character> map2 = new TreeMap <Integer, Character> ();

	public static String add (String t1, String t2) {
		int a = conv (t1), b = conv (t2);

		//System.out.println (a + "+" + b);

		if (a + b > 1000) {
			return "CONCORDIA CUM VERITATE";
		}
		else {
			return conv (a + b);
		}
	}

	public static String conv (int t) {
		StringBuilder a = new StringBuilder ();
		Map.Entry <Integer, Character> entry;

		while (t > 0) {
			entry = map2.floorEntry (t);

			if (entry != null) {
				a.append (entry.getValue ());
				t -= entry.getKey ();
			}
			else {
				break;
			}
		}


		//System.out.println ("BEFORE TRANS: " + a);
		char c, d;

		for (int i = 0; i < a.length (); i++) {
			if (i + 4 <= a.length ()) {
				if (a.charAt (i) == a.charAt (i + 1) && a.charAt (i + 1) == a.charAt (i + 2) && a.charAt (i + 2) == a
						.charAt (i + 3)) {
					c = a.charAt (i);
					d = map2.higherEntry (map.get (a.charAt (i - (i > 0 ? 1 : 0)))).getValue ();
					a.delete (i - (i > 0 ? 1 : 0), i + 4);

					if (c == 'I') {
						a.insert (i - (i > 0 ? 1 : 0), "I" + d);
					}
					else if (c == 'X') {
						a.insert (i - (i > 0 ? 1 : 0), "X" + d);
					}
					else if (c == 'C') {
						a.insert (i - (i > 0 ? 1 : 0), "C" + d);
					}
				}
			}
		}

		return a.toString ();
	}

	public static int conv (String t) {
		int val = 0;

		for (int i = 0; i < t.length (); i++) {
			if (i < t.length () - 1) {
				if (t.charAt (i) == 'I' && (t.charAt (i + 1) == 'V' || t.charAt (i + 1) == 'X')) {
					val += (map.get (t.charAt (i + 1)) - map.get (t.charAt (i)));
					i++;
				}
				else if (t.charAt (i) == 'X' && (t.charAt (i + 1) == 'L' || t.charAt (i + 1) == 'C')) {
					val += (map.get (t.charAt (i + 1)) - map.get (t.charAt (i)));
					i++;
				}
				else if (t.charAt (i) == 'C' && (t.charAt (i + 1) == 'D' || t.charAt (i + 1) == 'M')) {
					val += (map.get (t.charAt (i + 1)) - map.get (t.charAt (i)));
					i++;
				}
				else {
					val += map.get (t.charAt (i));
				}
			}
			else {
				val += map.get (t.charAt (i));
			}
		}

		return val;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int T = Integer.parseInt (in.readLine ());
		String[] t;

		map.put ('I', 1);
		map.put ('V', 5);
		map.put ('X', 10);
		map.put ('L', 50);
		map.put ('C', 100);
		map.put ('D', 500);
		map.put ('M', 1000);

		map2.put (1, 'I');
		map2.put (5, 'V');
		map2.put (10, 'X');
		map2.put (50, 'L');
		map2.put (100, 'C');
		map2.put (500, 'D');
		map2.put (1000, 'M');

		for (int i = 0; i < T; i++) {
			t = in.readLine ().split ("\\+");
			t[1] = t[1].substring (0, t[1].length () - 1);

			//System.out.println (conv (t [0]) + "+" + conv (t [1]) + "=" + add (t [0], t [1]));

			System.out.println (t[0] + "+" + t[1] + "=" + add (t[0], t[1]));
		}
	}
}