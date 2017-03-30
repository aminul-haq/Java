import java.util.*;

public class Pair implements Comparable<Pair>{
    public int a, b; 
    
    public Pair(int aa, int bb){
      a = aa; b = bb;
    }
    
    
    @Override
    public int hashCode() {
      return Long.valueOf((a * 31 + b)).hashCode();
    }
    @Override
    public boolean equals( Object p){
      if(this == p) return true;
      if(p == null ) return false;
      if (getClass() != p.getClass())
        return false;
      Pair pp = (Pair) p;
      return a == pp.a && b == pp.b;
    }
    
    public int compareTo(Pair p){
      return a - p.a;
    }
    
    public String toString(){
      return a+" "+b;
    }
  }