import java.util.ArrayList;

/**
7.1.1
    Makes a new hashset s of strings
    adds “hello” to hashset s
    adds “bye” to hashset s
    s becomes the union of hashset s and s which means it pretty much does nothing
    Makes a new treeset t of strings
    adds “123” to treeset t
    s becomes the union of hashset s and treeset t which means “123” is added to s
    prints whether s has all the things in t: True
    prints whether t has all the things in s: False
    prints whether s has the string “ace”: False
    prints whether s has the string “123”: True
    only keeps elements that are in treemap in setmap s
    True Checks if s contains the element “123”
    Only keeps elements in s in t
    True Checks if t contains “123”
     
7.1.4
	 setACopy is needed because if you were to call the methods using the setACopy with a reference to setA would edit setA rather than the intended setACopy. If you used setACopy = setA; then you would be making changes to setA since setACopy would reference the setA object. In this case, it would first do the addAll then do retainAll both on setA.

*/ 

public class ActualSetTester_Johnson {
     public static void main(String args[]) {
          Set<String> s1 = new Set<String>();
          s1.add("Ryan");
          s1.add("Leedle");
          s1.add("Derp");
          
          
          Set<String> s2 = new Set<String>();
          s2.add("Ayy Lmao");
          s2.add("String");
          s2.add("Herp");
          System.out.println("Set 1: " + s1 );
          System.out.println("Set 2: " + s2);
          
          
          s1.retainAll(s2);
          
          System.out.println("A: \nS1 (intersection) S2 --> " + s1);
          s1.clear();
          s1.add("Ryan");
          s1.add("Leedle");
          s1.add("Derp");
          
          s1.addAll(s2);
          System.out.println("B: \nS1 (union) S2 --> " + s1);
          s1.clear();
          s1.add("Ryan");
          s1.add("Leedle");
          s1.add("Derp");
          
          s1.removeAll(s2);
          System.out.println("C: \na-b --> " + s1);
          s1.clear();
          s1.add("Ryan");
          s1.add("Leedle");
          s1.add("Derp");
          
          Set<String> s3 = new Set<String>();
          
          if(s1.containsAll(s2)) {
               s3= s1;
          } else {
               s3 = s2;
          }
          
          
          System.out.println("D: \nif(s1 (subset) s2)\n\ts3=s1\nelse\n\ts3=s2");
          System.out.println("s3 --> " + s3);
          
     }
}
/*
9.11 * 10^31 mass of electron
(1.6 * 10^-19) * e = C
8.99 * 10 ^9
*/
class Set<E> {
     ArrayList<E> set = new ArrayList<E>(100);
     
     public Set() {
          
     }
     
     public boolean clear() {
          set = new ArrayList<E>(100);
          return true;
     }
     
     public boolean add(E obj) {
          for(E element : set) {
               if(element.equals(obj)) {
                   return false;
               }
          }
          set.add(obj);
          return true;
     } 
     
     public ArrayList<E> toArray() {
          return set;
     }
     
     //Diff
     public boolean removeAll(Set<E> s) {
          ArrayList<E> setList = s.toArray();
          for(E t : setList) {
               for(int i = 0; i < set.size(); i++) {
                    if(t.equals(set.get(i))) {
                         set.remove(i);
                    }
               }
          }
          return true;
     }
     //Intersect
     public boolean retainAll(Set<E> s) {
          ArrayList<E> setList = s.toArray();
          ArrayList<E> retainedList = new ArrayList<E>(set.size());
          for(E t:setList) {
               for(int i = 0; i < set.size(); i++) {
                    if(t.equals(set.get(i))) {
                         retainedList.add(t);
                    }
               }
          }
          return true;
     }
     
     //Union
     public boolean addAll(Set<E> s) {
          ArrayList<E> sList = s.toArray();
          for(E element : sList) {
               this.add(element);
          }
          return true;
     }
     
     //Contains All?
     public boolean containsAll(Set<E> s) {
          ArrayList<E> sList = s.toArray();
          for(E element : sList) {
               boolean returnBool = false;
               for(E obj: set) {
                    returnBool = element.equals(obj) || returnBool;
               }
               if(!returnBool) return false;
          }
          return true;
     }
     
     //Convert to string
     public String toString() {
          String returnString = "";
          returnString += set.get(0) + ", ";
          for(int i = 1; i<set.size()-1; i++) {
               returnString += set.get(i) + ", ";
          }
          return returnString+set.get(set.size()-1);
          
     }
}