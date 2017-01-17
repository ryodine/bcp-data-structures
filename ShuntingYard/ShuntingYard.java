import java.util.Scanner;
import java.lang.StringBuffer;
import java.lang.ArithmeticException;
import java.util.EmptyStackException;


/**
 * Shunting Yard Tester
 */
public class ShuntingYard {
    /**
     * Reads Arithmetic expressions through user input and calculates
     */
    public static void main(String[] args) {
        String command = "";
        Scanner input = new Scanner(System.in);
        Calc calc = new Calc();
        do {
            System.out.print("> ");
            command = input.nextLine();
            
            Double answer = calc.calculateExpr(command);
            System.out.println(answer);
        } while (!command.trim().equals("exit"));
    }
}

/**
 * Shunting Yard Calculator
 */
class Calc {
    //MEMORY
    public Double MEMORY = 0.0;
    
    //Operators
    public Stack<Character> operators = new Stack<Character>();
    //Operands
    public Stack<Double> operands = new Stack<Double>();
    
    /**
     * Returns Precedence of an operation 1-3
     */
    public static int getPrecedence(Character op) {
        String oper = op + "";
        if (oper.equals("^")) {
            return 3;
        } else if (oper.equals("*") || oper.equals("/")) {
            return 2;
        } else {
            return 1;
        }
    }    
    
    /**
     * Determines if first parameter has greater or equal precedence to second parameter
     */
    public static boolean hasPrecedence(Character op1, Character op2){
        return getPrecedence(op1) >= getPrecedence(op2);
    }
        
    public static Double evaluate(Character operator, Double op1, Double op2) {
        switch (operator + "") {
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "^":
                return Math.pow(op1, op2);
        }
        throw new ArithmeticException("Invalid Operation: " + operator);
    }
    
    /**
     * Evaluates Expression
     */
    public Double calculateExpr(String expr) {
        
        //reset stacks
        operators = new Stack<Character>();
        operands = new Stack<Double>();
        
        //Create a buffer for multicharacter numbers
        StringBuffer buff = new StringBuffer();
        
        //Parenthesis detection
        boolean ParenthesisMode = false;
        int numParens = 0;
        StringBuffer parenBuffer = new StringBuffer();    
        
        for (int i = 0; i < expr.length(); i++) {
            int charCode = (int)expr.charAt(i);
            
            //Triggered by open parenthesis below. keeps track of number of open v close
            if (ParenthesisMode) {
                if (charCode == 40) {
                    //(
                    numParens++;
                    parenBuffer.append((char)charCode);
                } else if (charCode == 41) {
                    //)
                    numParens--;
                    if (numParens == 0) {
                        //Calculate the parenthetical expression (recursively)
                        operands.push(new Calc().calculateExpr(parenBuffer.toString()));
                        ParenthesisMode = false;
                        parenBuffer = new StringBuffer();
                        numParens = 0;
                    } else {
                        parenBuffer.append((char)charCode);
                    }
                } else {
                    parenBuffer.append((char)charCode);
                }
            } else {
                if (charCode <= 57 && charCode >= 46 && charCode != 47) {
                    //Numbers
                    buff.append(expr.charAt(i));
                    if (i == expr.length() - 1 && buff.length() > 0) {
                        operands.push(Double.parseDouble(buff.toString()));
                    }
                } else {
                   if (buff.length() > 0) {
                        operands.push(Double.parseDouble(buff.toString()));
                        buff = new StringBuffer();
                    } 
                }
                if (charCode == 40) {
                    //(
                    ParenthesisMode = true;
                    numParens = 1;
                } else if (charCode == 41) {
                    //)       
                } else if (charCode == 42 || charCode == 43 || charCode == 45 || charCode == 47 || charCode == 94) {
                    //*+-/
                    
                    //If operator is the first item in string, then look to memory for Previous Parameter
                    if (operands.size() == 0 && operators.size() == 0) {
                        operands.push(MEMORY);
                    } 
                    operators.push((char) charCode);
                    StackDo(false);
                    
                }
            }
        }
        
        //Single Number
        if (operators.size() == 0) {
            MEMORY = operands.pop();
            return MEMORY;
        }
        
        //Final calculation of yard
        StackDo(true);
        Double opstore = operands.pop();
        Double val = evaluate(operators.pop(), operands.pop(), opstore);
        MEMORY = val;
        return val;
    }
    
    /**
     * Operator Solving Logic: Used Internally as helper function to calculateExpr
     * @param end: Signals final calculations of yard
     */
    private void StackDo(Boolean end) {
        
        boolean isAbleToCompute = true;
        //Enough to compare
        while (operators.size() >= 2 && isAbleToCompute) {
            
            boolean lastOper = false;
            if (operands.size() > operators.size()) {
                //use last operator
                lastOper = true;
            } else {
                
            }
            
            Character op2 = operators.pop();
            Character op1 = operators.pop();
            
            
            
            if (hasPrecedence(op1, op2)) {
                
                Double and2 = operands.pop();
                Double and1 = operands.pop();
                if (lastOper) {
                    operators.push(op1);
                    operands.push(evaluate(op2, and1, and2));
                } else {
                    operators.push(op2);
                    operands.push(evaluate(op1, and1, and2));
                }
                
            } else if (end) {
                Double and2 = operands.pop();
                Double and1 = operands.pop();
                operands.push(evaluate(op2, and1, and2));
                operators.push(op1);
            } else {
                isAbleToCompute = false;
                operators.push(op1);
                operators.push(op2);
            }

        }
    }
}

class Stack<T> {
    
    private Node head;
    private int size = 0;
    
    /**
     * returns if stack is empty as boolean
     */
    public boolean isEmpty() {
        return (head == null);
    }
    
    /**
     * pushes a node to the top and returns what you just pushed
     */
    public T push(T data) {
        head = new Node(data, head);
        size++;
        return data;
    }
    
    /**
     * returns and removes top node
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T oldVal = head.data;
            head = head.next;
            size--;
            return oldVal;
        }
    }
    
    /**
     * returns top node
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return head.data;
        }
    }
    
    /**
     * returns size
     */
    public int size() {
        return size;
    }
    
    private class Node 	{ 
		public T data;
		public Node next;
		public Node(T dat, Node nex) {
		    data = dat;
		    next = nex;
		}
	}
}