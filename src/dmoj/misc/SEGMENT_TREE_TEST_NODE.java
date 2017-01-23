import java.io.*;
import java.util.*;

public class SEGMENT_TREE_TEST_NODE {
 
 static Node [] tree;
 static int [] arr;
 static int SIZE;
 
 public static int gcd (int a, int b) {
  return b == 0 ? a : gcd (b, a % b);
 }
 
 public static int l_child (int pos) {
  return 2 * pos + 1;
 }
 
 public static int r_child (int pos) {
  return 2 * pos + 2;
 }
 
 public static void merge_range (int pos) {
  tree [pos].l = tree [l_child (pos)].l; tree [pos].r = tree [r_child (pos)].r;
  tree [pos].min = Math.min (tree [l_child (pos)].min, tree [r_child (pos)].min);
  tree [pos].gcd = gcd (tree [l_child (pos)].gcd, tree [r_child (pos)].gcd);
  
  tree [pos].count = 0;
  
  if (tree [pos].gcd == tree [l_child (pos)].gcd) {
   tree [pos].count += tree [l_child (pos)].count;
  }
  
  if (tree [pos].gcd == tree [r_child (pos)].gcd) {
   tree [pos].count += tree [r_child (pos)].count;
  }
 }
 
 public static void build (int pos, int l, int r) {
  //out.println (pos + " " + l + " " + r);
  
  tree [pos].l = l; tree [pos].r = r;
  
  if (l == r) {
   tree [pos].min = tree [pos].gcd = arr [l];
   tree [pos].count = 1;
  }
  else {
   int mid = (l + r) / 2;

   build (l_child (pos), l, mid);
   build (r_child (pos), mid + 1, r);
   
   merge_range (pos);
  }
 }
 
 public static void update (int t_pos, int a_pos, int v) {
  //out.println ("current pos = " + t_pos + " -> [" + tree [t_pos].l + ":" + tree [t_pos].r + "] arr pos = " + a_pos + " val " + v);
  
  if (tree [t_pos].l == a_pos && tree [t_pos].r == a_pos) {
   tree [t_pos].min = tree [t_pos].gcd = v;
   tree [t_pos].count = 1;
  }
  else {
   int mid = (tree [t_pos].l + tree [t_pos].r) / 2;
   
   if (a_pos <= mid) {
    update (l_child (t_pos), a_pos, v);
   }
   else {
    update (r_child (t_pos), a_pos, v);
   }
   
   merge_range (t_pos);
  }
 }
 
 public static int query_min (int pos, int l, int r) {
  if (l == tree [pos].l && r == tree [pos].r) {
   return tree [pos].min;
  }
  
  int mid = (tree [pos].l + tree [pos].r) / 2;
  
  if (r <= mid) {//if range is completely in left child
   return query_min (l_child (pos), l, r);
  }
  else if (l > mid) {//if range is completely in right child
   return query_min (r_child (pos), l, r);
  }
  
  //if range spans across both children
  return Math.min (query_min (l_child (pos), l, mid), query_min (r_child (pos), mid + 1, r));
 }
 
 public static int query_gcd (int pos, int l, int r) {
  if (l == tree [pos].l && r == tree [pos].r) {
   return tree [pos].gcd;
  }
  
  int mid = (tree [pos].l + tree [pos].r) / 2;
  
  if (r <= mid) {//if range is completely in left child
   return query_gcd (l_child (pos), l, r);
  }
  else if (l > mid) {//if range is completely in right child
   return query_gcd (r_child (pos), l, r);
  }
  
  //if range spans across both children
  return gcd (query_gcd (l_child (pos), l, mid), query_gcd (r_child (pos), mid + 1, r));
 }
 
 public static int query_count (int pos, int l, int r) {//tree pos, query range
  if (tree [pos].l == l && tree [pos].r == r) {
   return tree [pos].count;
  }
  
  int mid = (tree [pos].l + tree [pos].r) / 2;
  
  if (r <= mid) {
   return query_count (l_child (pos), l, r);
  }
  else if (l > mid) {
   return query_count (r_child (pos), l, r);
  }
  
  int l_gcd = query_gcd (l_child (pos), l, mid), r_gcd = query_gcd (r_child (pos), mid + 1, r), p_gcd = gcd (l_gcd, r_gcd);
  int l_count = query_count (l_child (pos), l, mid), r_count = query_count (r_child (pos), mid + 1, r), p_count = 0;
  
  if (p_gcd == l_gcd) {
   p_count += l_count;
  }
  
  if (p_gcd == r_gcd) {
   p_count += r_count;
  }

  return p_count;
 }
 
 public static void main (String [] t) throws IOException {
  BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
     PrintWriter out = new PrintWriter (System.out);

  t = in.readLine ().split (" ");
  int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
  double height = Math.log (N) / Math.log (2); SIZE = (int) Math.ceil (Math.pow (2, height + 2));//SIZE set larger than needed
  
  arr = new int [N]; tree = new Node [SIZE];
  
  t = in.readLine ().split (" ");
  
  for (int n = 0; n < N; ++n) {
   arr [n] = Integer.parseInt (t [n]);
  }
  
  for (int n = 0; n < SIZE; ++n) {
   tree [n] = new Node ();
  }
  
  build (0, 0, N - 1);
  
  int a, b;
  
  for (int m = 0; m < M; ++m) {
   t = in.readLine ().split (" ");
   a = Integer.parseInt (t [1]) - 1; b = Integer.parseInt (t [2]) - 1;
   
   if (t [0].charAt (0) == 'C') {
    update (0, a, b + 1);
   }
   else if (t [0].charAt (0) == 'M') {
    out.println (query_min (0, a, b));
   }
   else if (t [0].charAt (0) == 'G') {
    out.println (query_gcd (0, a, b));
   }
   else {
    out.println (query_count (0, a, b));
   }
  }
  
  out.close ();
 }

 private static class Node {
  int l, r, min, gcd, count;

  public Node () {}
 }
}