/**
 * Questions From Assignment
 * 
 * R3.12
 *  Moves the amount from this bank account to that bank account.
 *  new BankAccount().mystery(new BankAccount(), 500);
 * 
 * R3.15
 *  The error in this program is that when you call the grow() method the class does not update the area
 * 
 * R4.6 c, e
 *  c. 12.5
 *  e. 1
 * 
 * R4.7 d,e,f
 *  d. 17.5
 *  e. 18
 *  f. 18
 * 
 * R4.10
 *  System.in in quotes
 *  x is set twice, which will create an unintended result
 *  “The sum is ” + x + y does concatenation
 * 
 * R4.11
 *  Multiply by 100, then round, then divide by 100. Math.round(x*100)/100
 * 
 * R4.15
 *  read name and assign it to a variable called full_name
 *  create empty string in a variable called initials
 *  Cycle through all of the characters in the name:
 * 	    If the character is a capital letter then add it to initials variable
 *  Print the initials variables
 * 	
 * R4.16
 *  Convert the number to a string and assign that a variable string_valued
 *  Take the substring of the first character and save it as first
 *  Take the substring of the last character and save it as last
 *  print out first then “and” then last
 * 
 * You are a new developer at Seoul Consultancy Group, you have been assigned the pseudocode in 4.21 to implement. Rise to the rank of Senior Programmer IV if you do it correctly.
 * /



/**
 * Tester class for letterswap method.
 * Includes the method
 */
public class BookAssign_Johnson {
    
    /**
     * Tests the letterswap method
     */
    public static void main(String[] args){
        System.out.println(letterswap("ABCDEFG", 1, 2));
    }
    
    /**
     * This method implements the code in R4.21. It takes an string, then 2 integer positions starting at 0 and ending at length-1.
     * Swaps these letters and returns the resulting string
     * 
     * @param word  String to have the swap operation on
     * @param pos1  First letter to swap
     * @param pos2  Second letter to swap
     * 
     * @return      The resulting swapped string
     */
    public static String letterswap(String word, int pos1, int pos2) {
        String firstGroup = word.substring(0, pos1);
        String middleGroup = word.substring(pos1+1, pos2);
        String lastGroup = word.substring(pos2 + 1, word.length());
        
        return firstGroup + word.substring(pos2, pos2+1) + middleGroup + word.substring(pos1, pos1+1) + lastGroup;
    }
}