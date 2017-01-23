import java.io.*;
import java.util.regex.*;

public class FIND_DATES {
  
  public static boolean leap (int year) {
    return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    StringBuilder process = new StringBuilder ();
    String s;
    
    for (int i = 0; i < N; i++) {
      process.append (in.readLine ());
    }
    
    s = process.toString ();
    
    Pattern p = Pattern.compile ("(?<=[^A-Za-z\\d]{1})(\\d{4}-\\d{2}-\\d{2})(?=[^A-Za-z\\d]{1})");
    Matcher m = p.matcher (s);
    
    while (m.find ()) {
      String a = m.group ();
      String [] tokens = a.split ("-");
      
      int month = Integer.parseInt (tokens [1]);
      int day = Integer.parseInt (tokens [2]);
      
      if (month > 0 && month <= 12 && day > 0 && day <= 31) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
          System.out.println (a);
        }
        else if ((month == 4 || month == 6 || month == 9 || month == 11) && day != 31) {
          System.out.println (a);
        }
        else if (month == 2 && day <= 29) {
          if (day <= 28 || day == 29 && leap (Integer.parseInt (tokens [0]))) {
            System.out.println (a);
          }
        }
      }
    }
  }
}