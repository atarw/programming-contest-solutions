import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DMOPC_14_P1_NOT_ENOUGH_USERS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		int K = Integer.parseInt (in.readLine ());
		int D = Integer.parseInt (in.readLine ());

		System.out.println ((int) (N * Math.pow (K, D)));
	}
}