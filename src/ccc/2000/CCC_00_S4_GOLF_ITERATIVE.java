import java.io.*;
import java.util.*;

public class CCC_00_S4_GOLF_ITERATIVE {
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		int D = Integer.parseInt (in.readLine ()), N = Integer.parseInt (in.readLine ());
		int [] arr = new int [N], dp = new int [D + 1];

		for (int n = 0; n < N; ++n)
			arr [n] = Integer.parseInt (in.readLine ());
		
		Arrays.fill (dp, Short.MAX_VALUE);
		dp [D] = 0;
		
		for (int d = D - 1; d >= 0; --d)
			for (int n = 0; n < N; ++n)
				if (d + arr [n] <= D)
					dp [d] = Math.min (dp [d], dp [d + arr [n]] + 1);
		
		System.out.print (dp [0] != Short.MAX_VALUE ? "Roberta wins in " + dp [0] + " strokes." : "Roberta acknowledges defeat.");
	}
}