import java.io.*;
import java.util.*;

public class P8 {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]);
    int Q = Integer.parseInt (t [1]);
    
    IntervalTree tree = new IntervalTree ();
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      
      if (t [0].equals ("0")) {//deconstruct
        tree.deconstruct (Integer.parseInt (t [1]), Integer.parseInt (t [2]));
        System.out.println (tree.max ());
      }
      else if (t [0].equals ("1")) {//construct
        tree.construct (Integer.parseInt (t [1]), Integer.parseInt (t [2]));
        System.out.println (tree.max ());
      }
      else {//bot
        tree.bot ();
      }
      
      //System.out.println (tree.map);
      //System.out.println (tree.map2);
      //System.out.println (tree.map3);
    }
  }
}

class IntervalTree {
  
  NavigableMap <Integer, Integer> map = new TreeMap <Integer, Integer> ();//start to end
  NavigableMap <Integer, Integer> map2 = new TreeMap <Integer, Integer> ();//end to start
  NavigableMap <Integer, NavigableSet <Integer>> map3 = new TreeMap <Integer, NavigableSet <Integer>> ();//length to starts
  
  public int max () {
    if (!(map3.isEmpty () || map3.lastKey () < 0 || map3.lastEntry ().getValue ().isEmpty ())) {
      return map3.lastKey () + 1;
    }
    else {
      return 0;
    }
  }
  
  public void deconstruct (int S, int E) {
    NavigableMap <Integer, Integer> range = new TreeMap <Integer, Integer> (map);
    
    Map.Entry <Integer, Integer> v;
    //System.out.println (range);
    
    for (Iterator <Map.Entry <Integer, Integer>> i = range.entrySet ().iterator (); i.hasNext ();) {
      v = i.next ();
      
      if (v.getKey () >= S && v.getValue () <= E) {
        
        if (map.containsKey (v.getKey ()))
          map.remove (v.getKey ());
        
        if (map2.containsKey (v.getValue ()))
          map2.remove (v.getValue ());
        
        if (map3.containsKey (v.getValue () - v.getKey ())) {
          map3.get (v.getValue () - v.getKey ()).remove (v.getKey ());
          
          if (map3.get (v.getValue () - v.getKey ()).isEmpty ())
            map3.remove (v.getValue () - v.getKey ());
        }
      }
      else if (v.getKey () < S && v.getValue () < S) {//nothing
        
      }
      else if (v.getKey () > E) {//nothing
        
      }
      else if (v.getKey () < S && v.getValue () >= S && v.getValue () <= E) {
        
        if (map.containsKey (v.getKey ()))
          map.remove (v.getKey ());
        
        if (map2.containsKey (v.getValue ()))
          map2.remove (v.getValue ());
        
        if (map3.containsKey (v.getValue () - v.getKey ())) {
          map3.get (v.getValue () - v.getKey ()).remove (v.getKey ());
          
          if (map3.get (v.getValue () - v.getKey ()).isEmpty ())
            map3.remove (v.getValue () - v.getKey ());
        }
        
        map.put (v.getKey (), S - 1);
        map2.put (S - 1, v.getKey ());
        
        if (!map3.containsKey ((S - 1) - v.getKey ()))
          map3.put ((S - 1) - v.getKey (), new TreeSet <Integer> ());
        
        map3.get ((S - 1) - v.getKey ()).add (v.getKey ());
      }
      else if (v.getKey () >= S && v.getValue () > E) {
        
        if (map.containsKey (v.getKey ()))
          map.remove (v.getKey ());
        
        if (map2.containsKey (v.getValue ()))
          map2.remove (v.getValue ());
        
        if (map3.containsKey (v.getValue () - v.getKey ())) {
          map3.get (v.getValue () - v.getKey ()).remove (v.getKey ());
          
          if (map3.get (v.getValue () - v.getKey ()).isEmpty ())
            map3.remove (v.getValue () - v.getKey ());
        }
        
        map.put (E + 1, v.getValue ());
        map2.put (v.getValue (), E + 1);
        
        if (!map3.containsKey ((S - 1) - v.getKey ()))
          map3.put ((S - 1) - v.getKey (), new TreeSet <Integer> ());
        
        map3.get (v.getValue () - (E + 1)).add  (E + 1);
      }
      else if (v.getKey () < S && v.getValue () > E) {
        
        if (map.containsKey (v.getKey ()))
          map.remove (v.getKey ());
        
        if (map2.containsKey (v.getValue ()))
          map2.remove (v.getValue ());
        
        if (map3.containsKey (v.getValue () - v.getKey ())) {
          map3.get (v.getValue () - v.getKey ()).remove (v.getKey ());
          
          if (map3.get (v.getValue () - v.getKey ()).isEmpty ())
            map3.remove (v.getValue () - v.getKey ());
        }
        
        map.put (v.getKey (), S - 1);
        map.put (E + 1, v.getValue ());
        
        map2.put (S - 1, v.getKey ());
        map2.put (v.getValue (), E + 1);
        
        if (!map3.containsKey ((S - 1) - v.getKey ()))
          map3.put ((S - 1) - v.getKey (), new TreeSet <Integer> ());
        
        if (!map3.containsKey (v.getValue () - (E + 1)))
          map3.put (v.getValue () - (E + 1), new TreeSet <Integer> ());
        
        map3.get ((S - 1) - v.getKey ()).add (v.getKey ());
        map3.get (v.getValue () - (E + 1)).add (E + 1);
      }
    }
  }
  
  public void construct (int S, int E) {
    NavigableMap <Integer, Integer> range = new TreeMap <Integer, Integer> (map.subMap (S, true, E, true));//gives list of start values between area to be constructed
    
    Map.Entry <Integer, Integer> v;
    int maxE = E;
    
    for (Iterator <Map.Entry <Integer, Integer>> i = range.entrySet ().iterator (); i.hasNext ();) {
      v = i.next ();
      
      maxE = Math.max (maxE, v.getValue ());
      
      if (map.containsKey (v.getKey ()))
        map.remove (v.getKey ());
      
      if (map2.containsKey (v.getValue ()))
        map2.remove (v.getValue ());
      
      if (map3.containsKey (v.getValue () - v.getKey ()))
        map3.get (v.getValue () - v.getKey ()).remove (v.getKey ());
    }
    
    range = new TreeMap <Integer, Integer> (map2.subMap (S, true, E, true));
    int minS = S;
    
    for (Iterator <Map.Entry <Integer, Integer>> i = range.entrySet ().iterator (); i.hasNext ();) {
      v = i.next ();
      
      minS = Math.min (minS, v.getValue ());
      
      if (map.containsKey (v.getValue ()))
        map.remove (v.getValue ());
      
      if (map2.containsKey (v.getKey ()))
        map2.remove (v.getKey ());
      
      if (map3.containsKey (v.getKey () - v.getValue ()))
        map3.get (v.getKey () - v.getValue ()).remove (v.getValue ());
    }
    
    map.put (minS, maxE);
    map2.put (maxE, minS);
    
    if (!map3.containsKey (maxE - minS)) {
      map3.put (maxE - minS, new TreeSet <Integer> ());
    }
    map3.get (maxE - minS).add (minS);
  }
  
  public void bot () {
    if (!map3.isEmpty ()) {
      Integer s = map3.lastEntry ().getValue ().pollFirst ();
      
      if (s != null) {
        Integer e = map.remove (s);
        
        if (e != null) {
          map2.remove (e);
        }
      }
      
      if (map3.lastEntry ().getValue () == null || map3.lastEntry ().getValue ().isEmpty ()) {
        map3.pollLastEntry ();
      }
    }
  }
  
  public IntervalTree () {}
}