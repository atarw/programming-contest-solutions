import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// atharva washimkar
// August 11, 2018

public class DMOPC_15_P2_ITAMI_AND_SQUAD {

	static int soldiers;
	static int leaders;
	static int rank;

	public static int getTurn (int turn) {
		if (turn == leaders) {
			turn = 1;
		}
		else {
			turn++;
		}
		return turn;
	}

	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);

		String input = in.nextLine ();
		String[] content = input.split (" ");

		soldiers = Integer.parseInt (content[0]);
		leaders = Integer.parseInt (content[1]);
		rank = Integer.parseInt (content[2]);

		input = in.nextLine ();

		String[] strengthContent = input.split (" ");

		List<Integer> strengths = new ArrayList<Integer> ();

		for (int i = 0; i < strengthContent.length; i++) {
			strengths.add (Integer.parseInt (strengthContent[i]));
		}

		Collections.sort (strengths, Collections.reverseOrder ());

		int turn = 1;
		int sum = 0;
		int max = 0;

		while (!strengths.isEmpty ()) {
			max = strengths.get (0);

			if (turn == rank) {
				sum += max;
			}
			strengths.remove (0);

			turn = getTurn (turn);
		}

		System.out.println (sum);
	}
}