import java.io.*;
import java.util.*;

public class NOI_CHINA_98_P3_SOFTWARE_INSTALLATION_DISK {
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int M = Integer.parseInt (in.readLine ());
		int N = Integer.parseInt (in.readLine ());
		
		final int [] size = new int [N], ind = new int [N];
		List <Integer> [] list = new ArrayList [N];
		
		for (int n = 0; n < N; ++n)
			list [n] = new ArrayList <Integer> ();
		
		for (int n = 0; n < N; ++n) {
			t = in.readLine ().split (" ");
			size [n] = Integer.parseInt (t [0]);
			ind [n] = t.length - 1;
			
			for (int i = 1; i < t.length; ++i)
				list [Integer.parseInt (t [i]) - 1].add (n);
		}
		
		Queue <Integer> q = new PriorityQueue <Integer> (N, new Comparator <Integer> () {
			public int compare (Integer x, Integer y) {
				if (ind [x] == ind [y])
					return Integer.compare (size [x], size [y]);
				return Integer.compare (ind [x], ind [y]);
			}
		});
		
		for (int n = 0; n < N; ++n)
			if (ind [n] == 0)
				q.offer (n);
		
		int floppy = 0, cap = 0, u = 0, proc = 0;
		List <Integer> [] ord = new ArrayList [N];
		
		for (int n = 0; n < N; ++n)
			ord [n] = new ArrayList <Integer> ();
		
		while (!q.isEmpty ()) {
			u = q.poll ();
			++proc;
			
			if (cap + size [u] <= M)
				cap += size [u];
			else {
				++floppy; cap = size [u];
			}
			
			ord [floppy].add (u + 1);
			
			for (int v : list [u])
				if (--ind [v] == 0)
					q.offer (v);
		}
		
		if (proc != N)
			out.print (0);
		else {
			out.println (floppy + 1);
			
			for (int f = 0; f <= floppy; ++f) {
				for (int o : ord [f])
					out.print (o + " ");
				out.println ();
			}
		}

		out.close ();
	}
}