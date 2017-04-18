import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DMOPC_14_P4_PERFECT_TIMING {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int xS = Integer.parseInt (t[0]), yS = Integer.parseInt (t[1]);

		t = in.readLine ().split (" ");
		int xE = Integer.parseInt (t[0]), yE = Integer.parseInt (t[1]);

		t = in.readLine ().split (":");

		GregorianCalendar c = new GregorianCalendar ();
		c.set (Integer.parseInt (t[0]), Integer.parseInt (t[1]), Integer.parseInt (t[2]), Integer.parseInt (t[3]),
		       Integer.parseInt (t[4]), Integer.parseInt (t[5]));
		c.add (Calendar.SECOND, Math.abs (xE - xS) + Math.abs (yE - yS));

		int[] fields = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE,
		                Calendar.SECOND};
		int[] l = {4, 2, 2, 2, 2, 2};
		int a;

		if (!c.isLeapYear (c.get (Calendar.YEAR)) && c.get (Calendar.MONTH) == 2 && c.get (Calendar.DAY_OF_MONTH) ==
				29) {
			c.set (Calendar.MONTH, 3);
			c.set (Calendar.DAY_OF_MONTH, 1);
		}

		for (int f = 0; f < fields.length; f++) {
			if ((a = ("" + c.get (fields[f])).length ()) < l[f]) {
				for (int i = l[f] - a; i > 0; i--) {
					System.out.print ('0');
				}
				System.out.print (c.get (fields[f]) + (f == fields.length - 1 ? "" : ":"));
			}
			else {
				System.out.print (c.get (fields[f]) + (f == fields.length - 1 ? "" : ":"));
			}
		}
	}
}