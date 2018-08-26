import java.util.Scanner;

// atharva washimkar
// August 11, 2018

public class DMOPC_15_P3_ITAMI_AND_CIPHER {

	public static String shift (int shift, String input) {
		String msg = "";

		for (int i = 0; i < input.length (); i++) {
			msg += (shiftChar (shift, input.charAt (i)) + "");
		}

		return msg;
	}

	public static char shiftChar (int shift, char ab) {

		if (ab - shift < 'a') {
			int d1 = ab - 'a';
			int d2 = shift - d1;
			return (char) ('z' - d2 + 1);
		}
		else {
			return (char) (ab - shift);
		}
	}

	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);

		String code = "";
		String motto = "";
		String decrypt = "";
		int shift = 0;

		code = in.nextLine ();
		motto = in.nextLine ();

		for (int i = 0; i <= 25; i++) {
			decrypt = shift (i, code);

			if (decrypt.contains (motto)) {
				shift = i;
				break;
			}
		}

		System.out.println (shift);
		System.out.println (decrypt);
	}
}