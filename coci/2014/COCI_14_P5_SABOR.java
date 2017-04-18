import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class COCI_14_P5_SABOR {

	static List <Integer>[] list;
	static char[] arr;
	static int[] inter;

	public static void opp (int i) {
		arr[i] = (arr[i] == 'A' ? 'B' : 'A');
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ()), S, E;
		list = new ArrayList[N];
		arr = new char[N];
		inter = new int[N];

		Arrays.fill (arr, 'B');

		for (int d = 0; d < 5; d++) {
			t = in.readLine ().split (" +");

			//for (String i : t) {
			//  System.out.print ("'" + i + "' ");
			// }

			for (int i = 1; i < t.length / 2 + 1; i++) {
				S = Integer.parseInt (t[i * 2 - 1]) - 1;
				E = Integer.parseInt (t[i * 2]) - 1;

				if (list[S] == null) {
					list[S] = new ArrayList <Integer> ();
				}

				if (list[E] == null) {
					list[E] = new ArrayList <Integer> ();
				}

				list[S].add (E);
				list[E].add (S);

				inter[S]++;
				inter[E]++;
			}
		}

		while (true) {
			boolean change = false;

			for (int c = 0; c < arr.length; c++) {
				if (inter[c] > 2) {
					opp (c);
					change = true;

					if (list[c] != null) {
						for (int i = 0; i < list[c].size (); i++) {
							if (arr[c] == arr[list[c].get (i)]) {
								inter[c]++;
								inter[list[c].get (i)]++;
							}
							else {
								inter[c]--;
								inter[list[c].get (i)]--;
							}
						}
					}
				}
			}

			if (!change) {
				break;
			}
		}

		for (int c = 0; c < arr.length; c++) {
			System.out.print (arr[c]);
		}
	}
}