import java.io.*;
import java.util.*;

public class GermanNouns {
  
  public static List <String> getNouns (String line) throws UnsupportedEncodingException {
    List <String> list = new LinkedList <String> ();
    String [] tokens = line.split (" ");
    
    for (String i : tokens) {
      //System.out.print (i + " ");
      i = new String (i.getBytes (), "ISO-8859-1");
      
      if (Character.isUpperCase (i.codePointAt (0))) {
        list.add (i);
      }
    }
   // System.out.println ("\n");
    
    return list;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in, "ISO-8859-1"));
    PrintStream out = new PrintStream (System.out, true, "ISO-8859-1");
      
    int N = Integer.parseInt (in.readLine ());
    String input;
    List <String> list;
    
    for (int i = 0; i < N; i++) {
      input = new String (in.readLine ().getBytes (), "ISO-8859-1");
      list = getNouns (input);
      
      for (String x : list) {
        out.println (x);
      }
    }
  }
}