import java.io.*;
import java.util.*;

public class CCC_08_P3_GPS_TEST_ENTRY {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Map <Character, P> map = new HashMap <Character, P> ();
    
    map.put ('A', new P (0, 0));
    map.put ('B', new P (1, 0));
    map.put ('C', new P (2, 0));
    map.put ('D', new P (3, 0));
    map.put ('E', new P (4, 0));
    map.put ('F', new P (5, 0));
    
    map.put ('G', new P (0, 1));
    map.put ('H', new P (1, 1));
    map.put ('I', new P (2, 1));
    map.put ('J', new P (3, 1));
    map.put ('K', new P (4, 1));
    map.put ('L', new P (5, 1));
    
    map.put ('M', new P (0, 2));
    map.put ('N', new P (1, 2));    
    map.put ('O', new P (2, 2));
    map.put ('P', new P (3, 2));
    map.put ('Q', new P (4, 2));
    map.put ('R', new P (5, 2));
    
    map.put ('S', new P (0, 3));
    map.put ('T', new P (1, 3));
    map.put ('U', new P (2, 3));
    map.put ('V', new P (3, 3));
    map.put ('W', new P (4, 3));
    map.put ('X', new P (5, 3));
    
    map.put ('Y', new P (0, 4));
    map.put ('Z', new P (1, 4));
    map.put (' ', new P (2, 4));
    map.put ('-', new P (3, 4));
    map.put ('.', new P (4, 4));
    map.put ('~', new P (5, 4));//ENTER KEY
    
    String text = in.readLine () + "~";//ADD ENTER KEY
    int count = 0;
    P curr = map.get ('A');
    
    for (int i = 0; i < text.length (); i++) {
      count += Math.abs (curr.X - map.get (text.charAt (i)).X) + Math.abs (curr.Y - map.get (text.charAt (i)).Y);
      curr = map.get (text.charAt (i));
    }
    
    System.out.println (count);
  }
}

class P {
  int X, Y;
  
  public P (int X, int Y) {
    this.X = X;
    this.Y = Y;
  }
}