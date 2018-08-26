import java.util.Scanner;

// atharva washimkar
// August 11, 2018

public class DMOPC_15_P1_ITAMI_AND_MANGA {

	public static double bestRating;
	public static String bestName;

	public static double getRating (String input) {
		String rating = "";

		//System.out.println ("INPUT RATING: " + input);

		for (int i = 0; i < input.length (); i++) {
			if (Character.isDigit (input.charAt (i)) || input.charAt (i) == '.') {
				rating += (input.charAt (i) + "");
			}
		}

		//System.out.println ("'" + rating + "'");

		return Double.parseDouble (rating.trim ());
	}

	public static String getName (String input) {
		String name = "";

		for (int i = 0; i < input.length (); i++) {
			if (Character.isLetter (input.charAt (i)) || Character.isWhitespace (input.charAt (i))) {
				name += (input.charAt (i) + "");
			}
		}

		//System.out.println ("'" + name + "'");

		return name.trim ();
	}

	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);

		int listSize = in.nextInt ();
		String input;
		String name;
		double rating;

		in.nextLine ();

		for (int i = 0; i < listSize; i++) {
			input = in.nextLine ();

			name = getName (input);
			rating = getRating (input);

			if (rating > bestRating) {
				bestRating = rating;
				bestName = name;
			}
		}

		System.out.println (bestName);
	}
}