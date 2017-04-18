import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CCC_03_S3_FLOOR_PLAN {
  
  static char [][] maze;
  static int filled = 0;
  static int F;
  
  public static void fill () {
    Queue <P> queue = new LinkedList <P> ();
    List <Integer> rooms = new LinkedList <Integer> ();
    int area = 0;
    
    for (int r = 0; r < maze.length; r++) {
      for (int c = 0; c < maze [0].length; c++) {
        if (maze [r][c] == '.') {
          area++;
          maze [r][c] = Character.forDigit (rooms.size (), 10);
          queue.add (new P (r, c));
          P curr;
          
          while (!queue.isEmpty ()) {
            curr = queue.poll ();
            
            if (valid (curr.X + 1, curr.Y)) {
              queue.offer (new P (curr.X + 1, curr.Y));
              maze [curr.X + 1][curr.Y] = Character.forDigit (rooms.size (), 10);
              area++;
            }
            if (valid (curr.X - 1, curr.Y)) {
              queue.offer (new P (curr.X - 1, curr.Y));
              maze [curr.X - 1][curr.Y] = Character.forDigit (rooms.size (), 10);
              area++;
            }
            if (valid (curr.X, curr.Y + 1)) {
              queue.offer (new P (curr.X, curr.Y + 1));
              maze [curr.X][curr.Y + 1] = Character.forDigit (rooms.size (), 10);
              area++;
            }
            if (valid (curr.X, curr.Y - 1)) {
              queue.offer (new P (curr.X, curr.Y - 1));
              maze [curr.X][curr.Y - 1] = Character.forDigit (rooms.size (), 10);
              area++;
            }
          }
          
          rooms.add (area);
          area = 0;
        }
      }
    }
    
    /*for (int r = 0; r < maze.length; r++) {
     for (int c = 0; c < maze [0].length; c++) {
     System.out.print (maze [r][c] + " ");
     }
     System.out.println ();
     }*/
    
    Collections.sort (rooms);
    Collections.reverse (rooms);
    
    //System.out.println (rooms);
    
    for (int i = 0; i < rooms.size (); i++) {
      if (F < rooms.get (i)) {
        break;
      }
      
      F -= rooms.get (i);
      filled++;
    }
  }
  
  public static boolean valid (int r, int c) {
    return r >= 0 && r < maze.length && c >= 0 && c < maze [0].length && maze [r][c] == '.';
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    F = Integer.parseInt (in.readLine ());
    int R = Integer.parseInt (in.readLine ());
    int C = Integer.parseInt (in.readLine ());
    
    maze = new char [R][C];
    
    for (int r = 0; r < R; r++) {
      maze [r] = in.readLine ().toCharArray ();
    }
    
    fill ();
    
    System.out.println (filled + " room" + (filled != 1 ? "s, " : ", ") + F + " square metre(s) left over");
  }

	private static class P {

		int X, Y;

		public P (int X, int Y) {
			this.X = X;
			this.Y = Y;
		}

		public int hashCode () {
			return X * 17 + Y * 37;
		}

		public boolean equals (Object o) {
			P a = (P) (o);

			return X == a.X && Y == a.Y;
		}
  }
}