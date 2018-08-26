import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class HEARTH {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]);
		int T = Integer.parseInt (t[1]);
		Card[] cards = new Card[N];

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			cards[n] = new Card (t[0], Integer.parseInt (t[1]));
		}

		Arrays.sort (cards);

		for (int x = 0; x < cards.length; x++) {
			for (int y = x + 1; y < cards.length; y++) {
				for (int i = y + 1; i < cards.length; i++) {
					if (cards[x].C + cards[y].C + cards[i].C <= T) {
						System.out.println (cards[x].name + " " + cards[y].name + " " + cards[i].name);
					}
				}
			}
		}
	}

	private static class Card implements Comparable<Card> {

		String name;
		int C;

		public Card (String name, int C) {
			this.name = name;
			this.C = C;
		}

		public int compareTo (Card c) {
			return this.name.compareTo (c.name);
		}
	}
}