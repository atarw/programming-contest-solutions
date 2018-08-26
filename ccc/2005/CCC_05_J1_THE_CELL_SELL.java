import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class CCC_05_J1_THE_CELL_SELL {

	public static double cost (int D, int E, int W, int dLim, double dRate, double eRate, double wRate) {
		double dCost, eCost = eRate * E, wCost = wRate * W;

		if (D <= dLim) {
			dCost = 0;
		}
		else {
			dCost = (D - dLim) * dRate;
		}

		return dCost + eCost + wCost;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		DecimalFormat df = new DecimalFormat ("#.##");

		int D = Integer.parseInt (in.readLine ());
		int E = Integer.parseInt (in.readLine ());
		int W = Integer.parseInt (in.readLine ());

		double A = Double.valueOf (df.format (cost (D, E, W, 100, 0.25, 0.15, 0.2))), B = Double.valueOf (df.format (B = cost (D, E, W, 250, 0.45, 0.35, 0.25)));

		System.out.println ("Plan A costs " + A + "\nPlan B costs " + B);

		if (A > B) {
			System.out.println ("Plan B is cheapest.");
		}
		else if (A < B) {
			System.out.println ("Plan A is cheapest.");
		}
		else {
			System.out.println ("Plan A and B are the same price.");
		}
	}
}