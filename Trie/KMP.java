public class KMP {

  // Members:
 char[] T; // This is the text
  char[] P; // This is pattern
 int [] fail; // Failure function for pattern

  // Constructors:

 KMP(char[] p, char[] t) {
   P = p; T = t;
   computeFail();
 } //

  // Methods:

 /******************************************************
  Routine to compute the failure function 
  ******************************************************/
 public void computeFail() {
   // init:
  fail = new int[P.length];
  fail[1] = 0;
   // loop:
  for (int k=2; k< fail.length; k++) {
    int kk = fail[k-1];
    while (kk>0 && (P[kk] != P[k-1]))
      kk = fail[kk];
    fail[k] = 1 + kk;
  }
 } // computeFail(P)

 /******************************************************
  THIS IS THE MAIN ROUTINE
  ******************************************************/
 public int find(int start) {
   // init:
  int j = start; // text index 
  int k = 1; // pattern index
   // loop:
  while (j < T.length) {
    if (k >= P.length) return(j - k + 1);
    if ((T[j] == P[k]) || (k==0)) {
      j++; k++;
    } else {
      k = fail[k]; // k could become 0
    }
  } // while
   // Not found:
  return(-1);
 } // find()

 /******************************************************
  prints data
  ******************************************************/
 void output() {
   System.out.print("> Pattern = \""); 
   for (int i=1; i< P.length; i++) 
  System.out.print(P[i]);
   System.out.print("\"\n> Text    = \""); 
   for (int i=1; i< T.length; i++) 
  System.out.print(T[i]);
   System.out.println("\"");
 } // output()


 /******************************************************
  Main method
  ******************************************************/
 public static void main( String[] args) {

   // sample input with 6 keys
   // (the first is a dummy key)

   // char[] p = {'0', 'o', 'u'};
   char[] p = {'0', 'y', 'o', 'u'};
   char[] t = {'0','a', 'r', 'e', ' ', 'y', 'o', 'u', ' ','a', ' ', 'y', 'o', 'u', 't', 'h', '?'};

   // construct a KMP object
   KMP m = new KMP(p, t);
   m.output();   // print data

   // find all matches
   int f = m.find(1);
   if (f<1)
    System.out.println(">>  No match found");
   else {
  while (f>=1) {
    System.out.println(">>  Match found at position " + f);
    f = m.find(f+1);
  }//while
   }//else
   } // main

}//class KMP