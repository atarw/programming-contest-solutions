import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class VMSS_16_P1_JAYDEN_WATCHES_VIDEOS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int L = Integer.parseInt (t[0]), X = Integer.parseInt (t[1]);

		for (int l = 1; l <= L * X; l++) {
			if (l / X > L - l) {
				System.out.print (l * X);
				break;
			}
		}
	}
}

//takes L * X to download completely
//