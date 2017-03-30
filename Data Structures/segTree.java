import java.util.*;

public class segTree{
  public int n, seg[];
  public segTree(int nn){
    n = nn;
    seg = new int[4*(int)1e5];
    //segTree = new int[5*n];
  }
  
  public void update(int pos,  int val){
    update(1, 1, n, pos, val);
  }
  
  public int query(int l, int r){
    return query(1, 1, n, l, r);
  }
  
  public void update(int p, int s, int e, int pos, int val){
    if(s == pos && e == pos){
      seg[p] = val;
      return;
    }
    if(s > pos || e < pos || s > e || e < s) return;
    
    int m = (s+e)/2, l = 2*p, r = 2*p + 1; 
    
    update(l, s, m, pos, val);
    update(r, m+1, e, pos, val);
    
    seg[p] = Math.max(seg[l], seg[r]);
  }
  
  
  public int query(int p, int s, int e, int a, int b){
    if(s >= a && e <= b){
      return seg[p];
    }
    if(s > b || e < a || s > e || e < s) return Integer.MIN_VALUE;
    
    int m = (s+e)/2, l = 2*p, r = 2*p + 1; 
    
    int q1 = query(l, s, m, a, b);
    int q2 = query(r, m+1, e, a, b);
    
    return Math.max(q1, q2);
  }
}