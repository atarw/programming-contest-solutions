import java.util.*;

public class CCC_11_S4_BLOOD_DISTRIBUTION {
  
  public static final int O_MINUS = 0, O_PLUS = 1, A_MINUS = 2, A_PLUS = 3, B_MINUS = 4, B_PLUS = 5, AB_MINUS = 6, AB_PLUS = 7;
  public static final int [] TYPE = {O_MINUS, O_PLUS, A_MINUS, A_PLUS, B_MINUS, B_PLUS, AB_MINUS, AB_PLUS};
  
  
  public List <Integer> getPossibleBlood (int type) {
    List <Integer> types = new ArrayList <Integer> ();
    
    types.add (type);
    
    if (type == O_PLUS) {
      types.add (O_MINUS);
    }
    else if (type == A_MINUS) {
      types.add (O_MINUS);
    }
    else if (type == B_MINUS) {
      types.add (O_MINUS);
    }
    else if (type == A_PLUS) {
      types.add (A_MINUS);
      
      types.add (O_PLUS);
      types.add (O_MINUS);
    }
    else if (type == B_PLUS) {
      types.add (B_MINUS);
      
      types.add (O_PLUS);
      types.add (O_MINUS);
    }
    else if (type == AB_MINUS) {
      types.add (A_MINUS);
      types.add (B_MINUS);
      
      types.add (O_MINUS);
    }
    else if (type == AB_PLUS) {
      types.add (AB_MINUS);
      
      types.add (A_PLUS);
      types.add (B_PLUS);
      
      types.add (A_MINUS);
      types.add (B_MINUS);
      
      types.add (O_PLUS);
      types.add (O_MINUS);
    }
    
    return types;
  }
  
  public int getHelped (int units, int patients) {
    if (patients > units) {
      return units;
    }
    else {
      return patients;
    }
  }
  
  public int getPeopleHelped (int [] units, int [] patients) {
    int helped = 0;
    List <Integer> possibleBlood;
    int h = 0;
    
    for (int i = 0; i < patients.length; i++) {
      if (patients [i] > 0) {
        possibleBlood = getPossibleBlood (i);
        
        for (int x = 0; x < possibleBlood.size () && patients [i] > 0; x++) {
          h = getHelped (units [possibleBlood.get (x)], patients [i]);
          helped += h;
          units [possibleBlood.get (x)] -= h;
          patients [i] -= h;
        }
      }
    }
    return helped;
  }
  
  public static void main (String [] args) {
    Scanner in = new Scanner (System.in);
    String input = in.nextLine ();
    CCC_11_S4_BLOOD_DISTRIBUTION s4 = new CCC_11_S4_BLOOD_DISTRIBUTION ();
    
    String [] tokens = input.split (" ");
    int [] units = new int [tokens.length];
    
    input = in.nextLine ();
    
    String [] tokens2 = input.split (" ");
    int [] patients = new int [tokens2.length];
    
    for (int i = 0; i < patients.length; i++) {
      units [i] = Integer.parseInt (tokens [i]);
      patients [i] = Integer.parseInt (tokens2 [i]);
    }
    System.out.println (s4.getPeopleHelped (units, patients));
  }
}