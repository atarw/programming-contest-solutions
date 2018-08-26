import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class MWC_15_C4_P5_ARTS_AND_CRAFTS {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), X = Integer.parseInt (t[1]), Y = Integer.parseInt (t[2]);

		NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer> ();//depth of cut to frequency
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer> (N);//start point to cut depth
		Set<Integer> set = new HashSet<Integer> (N);//vertical cuts

		int s, d, old;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			s = Integer.parseInt (t[1]);

			if (t[0].charAt (0) == 'h') {
				d = Integer.parseInt (t[2]);

				if (map2.containsKey (s)) {//cut was already made here
					old = map2.get (s);
					map2.put (s, Math.max (d, old));

					if (old < d) {//older cut was less deep
						if (map.get (old) > 1) {//more than one cut of old frequency
							map.put (old, map.get (old) - 1);
						}
						else {
							map.remove (old);
						}

						if (!map.containsKey (d)) {
							map.put (d, 1);
						}
						else {
							map.put (d, map.get (d) + 1);
						}
					}
				}
				else {//no cut was made here
					map2.put (s, d);

					if (!map.containsKey (d)) {//cut of same depth doesn't exist yet
						map.put (d, 1);
					}
					else {
						map.put (d, map.get (d) + 1);
					}
				}
			}
			else {
				set.add (s);
			}
		}

		int amt = 1, count = 1;
		amt += (map.containsKey (X) ? map.get (X) : 0);

		int[] pre = new int[map.keySet ().size () + 1];//prefix sum array
		NavigableMap<Integer, Integer> map3 = new TreeMap<Integer, Integer> ();//stores cut depth to index in psa
		Map.Entry<Integer, Integer> entry;

		for (int i : map.keySet ()) {
			pre[count] += map.get (i);
			map3.put (i, count);
			count++;
		}

		for (int x = 1; x < pre.length; x++) {
			pre[x] += pre[x - 1];
		}

		amt += set.size ();

		for (int i : set) {
			entry = map3.ceilingEntry (i);

			if (entry != null) {
				amt += pre[count - 1] - pre[entry.getValue () - 1];
			}
		}

		System.out.println (amt);
	}
}