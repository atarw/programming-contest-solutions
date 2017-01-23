import java.io.*;
import java.util.*;

public class TREE_CUTTING {
	
	static int [][] maze;
	
	public static List <State> next (State curr) {
		List <State> next = new ArrayList <State> ();

		if (curr.x + 1 < maze.length) {
			next.add (new State (curr.x + 1, curr.y));
		}

		if (curr.x - 1 >= 0) {
			next.add (new State (curr.x - 1, curr.y));
		}
		
		if (curr.y - 1 >= 0) {
			next.add (new State (curr.x, curr.y - 1));
		}
		
		// right
		if (curr.y + 1 < maze [0].length) {
			next.add (new State (curr.x, curr.y + 1));
		}
		
		return next;
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		t = in.readLine ().split (" ");
		int R = Integer.parseInt (t [0]), C = Integer.parseInt (t [1]);
		maze = new int [R][C];
		
		int sx = -1, sy = -1;
		int chr;
		
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c) {
				chr = in.read ();
				
				if (chr == 'X') {
					maze [r][c] = 0;
					sx = r; sy = c;
				}
				else if (chr == '.')
					maze [r][c] = 0;
				else
					maze [r][c] = Character.getNumericValue (chr);
				
				in.read ();
			}
		}
		
		int max_height = -1;
		int ex = -1, ey = -1;
		
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c) {
				if (max_height < maze [r][c] || max_height == maze [r][c] && Math.pow (sx - r, 2) + Math.pow (sy - c, 2) < Math.pow (sx - ex, 2) + Math.pow (sy - ey, 2)) {
					max_height = maze [r][c];
					ex = r; ey = c;
				}
			}
		}
				
		/*Knowing that they can only walk in cardinal directions, how many trees must they remove to arrive at their desired tree?
		The sum of the heights of the trees you cut must be the minimal over all possible sets of trees to cut to get to the tallest tree.
		Additionally, you should also minimize the number of trees summing to that minimal height in the event of a tie.
		
		One integer, the min number of trees Tatsumi and Mine will have to remove to get to their desired tree.*/
		
		State2 [][] cache = new State2 [R][C];
		
		for (int r = 0; r < R; ++r)
			for (int c = 0; c < C; ++c)
				cache [r][c] = new State2 (Short.MAX_VALUE, Short.MAX_VALUE);
			
		cache [sx][sy] = new State2 (0, 0);
				
		List <State> next;
		Queue <State> q = new ArrayDeque <State> ();
		State curr = new State (sx, sy);
		q.offer (curr);
		
		while (!q.isEmpty ()) {
			curr = q.poll ();
			
			if (curr.x != ex || curr.y != ey) {
				next = next (curr);
				
				for (State s : next) {
					if (cache [s.x][s.y].h > cache [curr.x][curr.y].h + maze [s.x][s.y] || cache [s.x][s.y].h == cache [curr.x][curr.y].h + maze [s.x][s.y] && cache [s.x][s.y].c > cache [curr.x][curr.y].c + (maze [s.x][s.y] == 0 ? 0 : 1)) {
						cache [s.x][s.y] = new State2 (cache [curr.x][curr.y].h + maze [s.x][s.y], cache [curr.x][curr.y].c + (maze [s.x][s.y] == 0 ? 0 : 1));
						q.offer (s);
					}
				}
			}
		}
		
		System.out.print (cache [ex][ey].c - 1);// since it counts the cutting of the tree they are after
	}

	private static class State {
		int x, y;

		public State (int x, int y) {
			this.x = x; this.y = y;
		}
	}

	private static class State2 {
		int h, c;

		public State2 (int h, int c) {
			this.h = h; this.c = c;
		}
	}
}