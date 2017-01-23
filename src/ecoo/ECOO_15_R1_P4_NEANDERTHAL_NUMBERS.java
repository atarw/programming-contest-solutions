import java.io.*;

public class ECOO_15_R1_P4_NEANDERTHAL_NUMBERS {
  
  static int [] cache;
  static String [] num = {"ook", "ookook", "oog", "ooga", "ug", "mook", "mookmook", "oogam", "oogum", "ugug"};
  
  public static int comb (String ln) {
    if (cache [cache.length - ln.length () - 1] == 0) {
      if (!ln.isEmpty ()) {
        for (String i : num) {
          if (ln.length () >= i.length () && ln.substring (0, i.length ()).equals (i)) {
            cache [cache.length - ln.length () - 1] += comb (ln.substring (i.length ()));
          }
        }
      }
      else {
        cache [cache.length - ln.length () - 1]++;
      }
    }
    
    return cache [cache.length - ln.length () - 1];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String ln;
    
    while ((ln = in.readLine ()) != null && !ln.isEmpty ()) {
      cache = new int [ln.length () + 1];//index of original word
      System.out.println (comb (ln));
    }
  }
}