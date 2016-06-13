import java.io.*;

public class ExamMark {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int P = Integer.parseInt (in.readLine ());
    int Q = Integer.parseInt (in.readLine ());
    int W = Integer.parseInt (in.readLine ());
    double minQ = Q - 0.5;
    
    double fWorth = W / 100.0;
    double cWorth = 1 - fWorth;
    
    double percentage = (minQ - P * cWorth) / fWorth;
    
    if (W == 0 && P >= minQ) {
      System.out.println (0);
    }
    else if (Math.round (percentage) <= 100) {
      System.out.println (Math.round (percentage));
    }
    else {
      System.out.println ("DROP THE COURSE");
    }
  }
}