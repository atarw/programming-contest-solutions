import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class JDCC_15_NOVEMBER_D_LUCKY_TICKET {
  
  static final boolean PRACTICE = false;
  
  public static String indent (int depth) { //USED TO TEST WHEN RECURSING
    StringBuilder indent = new StringBuilder (depth);
    
    for (int i = 0; i < depth; i++) {
      indent.append ("  ");
    }
    
    return indent.toString ();
  }
  
  /* CHECKS WHETHER LEFT SIDE SHOULD BE INCREMENTED, HAPPENS ONLY WHEN MAX POSSIBLE RIGHT SIDE IS LESS THAN OR EQUAL TO THE ACTUAL RIGHT SIDE
   * 
   * E.G. IN THE CASE 113 500, THE LEFT SIDE MUST BE INCREMENTED TO FIND THE NEXT LUCKY TICKET, WHICH IS 114 006
   */
  
  public static boolean shouldAdjustLS (int [] rs, int sumLS) {
    int [] maxRS = new int [rs.length];
    
    for (int i = 0, tempSumRS = sumLS; i < rs.length; i++) {
      if (tempSumRS >= 9) {
        maxRS [i] = 9;
        tempSumRS -= 9;
      }
      else {
        maxRS [i] = tempSumRS;
        tempSumRS = 0;
      }
      
      if (maxRS [i] > rs [i]) {//IF ANY OF THE DIGITS OF THE MAXIMUM RIGHT SIDE ARE GREATER THAN THE RIGHT SIDE'S DIGITS, THIS IMPLIES THE MAXIMUM IS GREATER THAN THE RIGHT SIDE AND THERE IS NO NEED TO INCREMENT THE LEFT SIDE
        return false;
      }
      else if (maxRS [i] < rs [i]) {//IF ANY OF THE DIGITS OF THE MAXIMUM RIGHT SIDE ARE LESS THAN THE RIGHT SIDE'S DIGITS, THIS IMPLIES THAT THE RIGHT SIDE IS GREATER THAN THE ALLOWED MAXIMUM AND THE LEFT SIDE SHOULD BE INCREMENTED
        return true;
      }
    }
    
    
    if (PRACTICE) {
      System.out.println ("\n\nMAX RS");
      for (int i = 0; i < maxRS.length; i++) {
        System.out.print (maxRS [i]);
      }
      System.out.println ("\n");
    }
    
    //IF THE PROGRAM REACHES HERE, THEN THE RIGHT SIDE = THE MAX RIGHT SIDE, AND SINCE WE NEED TO FIND THE NEXT LUCKY TICKET, THE LEFT SIDE SHOULD BE INCREMENTED
    return true;
  }
  
  
  /* ADJUSTS THE SIDE GIVEN RECURSIVELY UNTIL THE REMAINDER IS 0
   */
  public static boolean adjustSide (int [] side, int remainder, int index, boolean same) {
    if (PRACTICE)
      System.out.println (indent (index) + "REMAINDER: " + remainder + " INDEX: " + index + " SAME: " + same);
    
    //IF IT IS IMPOSSIBLE TO MAKE THE REMAINING DIGITS SUM TO REMAINDER, THE LAST DIGIT CHANGED IS WRONG, AND MUST BE REVERTED
    if (same && index == side.length - 1 && remainder <= side [index] || remainder > 9 * (side.length - index)) {
      if (PRACTICE)
        System.out.println (indent (index) + "PAST INDEX BOUNDS OR IMPOSSIBLE");
      return false;
    }
    else {
      //RANGE OF POSSIBLE DIGITS IS EITHER FROM 0-9 OR THE SIDE'S DIGIT AT THE SPECIFIC INDEX - 9, DEPENDING ON IF THE PREVIOUS DIGITS WERE MODIFIED OR NOT
      int start = same ? side [index] : 0;
      int old = 0;
      
      if (PRACTICE)
        System.out.println (indent (index) + "START: " + start);
      
      for (int i = start; i < 10; i++, same = false) {//same is changed after one iteration, as the left side is no longer the same as before
        old = side [index];//store old digit in case we need to revert
        side [index] = i;
        
        if (PRACTICE)
          System.out.println (indent (index) + "OLD: " + old + " NEW " + side [index]);
        
        if (index + 1 < side.length) {//IF WE HAVEN'T EXCEEDED THE ARRAY BOUNDS
          if (PRACTICE)
            System.out.println (indent (index) + "PROCEED TO NEXT DIGIT - REMAINDER " + remainder + " (ABOUT TO BE " + (remainder - side [index]) + ")\n");
          
          if (remainder - side [index] < 0) {//CANNOT USE THIS DIGIT, TOO HIGH/LOW, MUST INCREMENT PREVIOUS DIGIT BY 1
            if (PRACTICE)
              System.out.println ("CANNOT USE THIS DIGIT, TOO HIGH/LOW, MUST INCREMENT PREVIOUS");
            side [index] = old;
            return false;
          }
          else if (!adjustSide (side, remainder - side[index], index + 1, old == i && same)) {//IMPOSSIBLE TO ADJUST
	          // USING THIS DIGIT, MUST INCREMENT PREVIOUS BY 1
	          if (PRACTICE)
              System.out.println (indent (index) + "CANNOT USE THIS DIGIT, MUST INCREMENT PREVIOUS - INDEX " + index);
            side [index] = old;
            
            if (i == 9) {return false;}//IF YOU CANNOT INCREMENT THIS DIGIT, GO BACK TO LAST CALL OF ADJUSTSIDE () AND INCREMENT THAT DIGIT INSTEAD
          }
          else {//SUCCESSFUL ADJUSTMENT, NO MORE TO BE DONE HERE
            if (PRACTICE)
              System.out.println (indent (index) + "DONE");
            return true;
          }
        }
        else if (remainder < 10) {//IF THE REMAINDER IS < 10, SET THE LAST DIGIT TO REMAINDER, AND END THE METHOD
          if (PRACTICE)
            System.out.println (indent (index) + "LAST DIGIT, SET TO " + remainder);
          side [index] = remainder;
          break;
        }
        else {//IMPOSSIBLE TO FIND NEXT LUCKY TICKET WITH THE CURRENT PREVIOUS DIGITS, MUST INCREMENT PREVIOUS DIGIT BEFORE THIS
          if (PRACTICE)
            System.out.println (indent (index) + "LAST DIGIT, CANNOT BE SET TO " + remainder + " MUST INCREMENT PREVIOUS");
          side [index] = old;
          return false;
        }
      }
      
      return true;
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (PRACTICE ? new InputStreamReader (System.in) : new FileReader (new File ("D.in")));
    PrintWriter out = new PrintWriter (PRACTICE ? new OutputStreamWriter (System.out) : new FileWriter (new File ("D1.out")));
    int T = Integer.parseInt (in.readLine ());
    
    int [] ls, rs;
    int sumLS = 0;
    
    String ln;
    
    for (int i = 0; i < T; i++) {
      ln = in.readLine ();
      ls = new int [ln.length () / 2];
      rs = new int [ln.length () / 2];
      sumLS = 0;
      
      for (int x = 0; x < ln.length () / 2; x++) {
        ls [x] = Integer.parseInt (ln.charAt (x) + "");
        sumLS += ls [x];
      }
      
      for (int x = 0; x < ln.length () / 2; x++) {
        rs [x] = Integer.parseInt (ln.charAt (x + ln.length () / 2) + "");
      }
      
      boolean shouldAdjustLS = shouldAdjustLS (rs, sumLS);
      
      if (shouldAdjustLS) {//IF LEFT SIDE SHOULD BE ADJUSTED BEFORE FINDING LUCKY TICKET
        LinkedList <Integer> adjustedLS = new LinkedList <Integer> ();//USING BIGINTEGER IS TOO SLOW, MUST STORE EACH DIGIT IN AN INDEX, AND ADD 1
        
        boolean added = false;
        
        //BASICALLY ADDING 1 TO THE LEFT SIDE
        for (int x = ls.length - 1; x >= 0; x--) {
          if (!added) {
            if (ls [x] + 1 > 9) {
              ls [x] = 0;
            }
            else {
              added = true;
              ls [x]++;
            }
          }
          adjustedLS.addFirst (ls [x]);
        }
        
        if (!added) {
          adjustedLS.addFirst (1);
        }
        
        ls = new int [adjustedLS.size ()];//MOVE VALUES FROM LINKEDLIST TO ARRAY AFTER ADDING 1
        rs = new int [ls.length];//RESET RIGHT SIDE TO 0000...as the left side limit does not exist anymore
        sumLS = 0;
        Iterator <Integer> iterator = adjustedLS.iterator ();//LinkedList does not have O (1) access
        
        for (int x = 0; x < ls.length; x++)
        {
          ls [x] = iterator.next ();
          rs [x] = 0;
          sumLS += ls [x];
        }
        
        if (PRACTICE) {
          System.out.println ("\n\nADJUSTED LS");
          for (int x = 0; x < ls.length; x++) {
            System.out.print (ls [x]);
          }
          System.out.println ("\n");
        }
      }
      
      adjustSide (rs, sumLS, 0, !shouldAdjustLS);//ADJUST RIGHT SIDE BASED ON THE LEFT SIDE SUM
      
      for (int x = 0; x < ls.length; x++) {//PRINT OUT LEFT SIDE ANSWER
        if (PRACTICE)
          System.out.print (ls [x]);
        else
          out.print (ls [x]);
      }
      
      for (int x = 0; x < rs.length; x++) {//PRINT OUT RIGHT SIDE ANSWER
        if (PRACTICE)
          System.out.print (rs [x]);
        else
          out.print (rs [x]);
      }
      
      if (PRACTICE)
        System.out.println ();
      else
        out.println ();
    }
    
    if (!PRACTICE) {//USED TO TEST AGAINST TEST DATA
      out.close ();
      BufferedReader ansIn = new BufferedReader (new FileReader (new File ("D.out")));
      BufferedReader myIn = new BufferedReader (new FileReader (new File ("D1.out")));
      
      String ln1, ln2;
      int score = 0;
      
      while ((ln1 = myIn.readLine ()) != null && (ln2 = ansIn.readLine ()) != null) {
        if (ln1.equals (ln2)) {
          score++;
        }
        else {
          System.out.println (ln1 + " vs " + ln2);
        }
      }
      
      ansIn.close ();
      myIn.close ();
      
      System.out.println ("\nSCORE: " + score);
    }
  }
}