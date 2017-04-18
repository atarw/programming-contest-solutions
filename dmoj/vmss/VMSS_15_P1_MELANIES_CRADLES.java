import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VMSS_15_P1_MELANIES_CRADLES {

	public static int cradles (int radius) {
		int total = 0;

		for (int x = 1; x <= radius; x++) {
			total += Math.sqrt (radius * radius - x * x) + 1;
		}

		return total * 4 + 1;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		int R = Integer.parseInt (in.readLine ());
		System.out.println (cradles (R));
	}
}