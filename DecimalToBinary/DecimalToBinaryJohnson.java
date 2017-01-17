import java.util.Stack;
class DecimalToBinaryJohnson {
	/**
	  printBinary converts a decimal integer to its binary equivalent (and displays it to standard output).
	  preconditon: decimal > 0
	  */
	public static void printBinary( int decimal ) {
	    Stack<Integer> lmao = new Stack<Integer>();
		// Create stack to hold the integer values
		
		// While decimal is not equal to zero
		while (decimal != 0) {
			// Find remainder when decimal is divided by 2
			Integer ayy = decimal % 2;
			// push remainder onto the stack
			lmao.push(ayy);
			// divide decimal by 2 and assign result to decimal
			decimal = decimal/2;
			
		}
		// While stack isn't empty
		while (!lmao.isEmpty()) {
			
			// pop digit off top of stack and display
			System.out.print(lmao.pop());
			
		}
		System.out.println();
	
	}
	
	public static void main(String[] a) {
	
		int i = 10;
		printBinary(10);
		printBinary(9);
		printBinary(8);
	}		
}