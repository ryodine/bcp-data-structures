import java.lang.StringBuffer;
import java.lang.ArithmeticException;
import java.util.Stack;
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class basic_1_johnson {
 private static HashMap<String, Double> symbolTable = new HashMap<String, Double>();

    private static Scanner scanner = new Scanner(System.in);
    private static boolean _haltExecution = false;
    private static Calc eval = new Calc();

    public static void main(String[] args) {
        if (args.length >= 1) {
            FileReader reader;
            try {
                reader = new FileReader(args[0]);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line = null;
                while((line = bufferedReader.readLine()) != null && !_haltExecution) {
                    evaluate(line);
                }


            } catch (FileNotFoundException e) {
                System.out.println("File Not found");
                System.out.println(e);
            } catch (IOException e) {
                System.out.println("Error Reading file");
                System.out.println(e);
            }
        } else {
            System.out.println("Welcome to interactive BASIC");
            while (!_haltExecution) {
                evaluate(stdin(">>> "));
            }
        }
    }

    private static void evaluate(String line) {
        try {
            if (catchCommand(line, "print")) {
                String params = getParams(line, "print");
                if (containsOperator(params)) {
                    //Evaluating a numerical thing
                    stdout(eval.calculateExpr(replaceVarsWithValues(params)));

                } else {
                    //Print the variable
                    if (params.length() > 0) {
                        stdout(eval.calculateExpr(replaceVarsWithValues(params)));
                    } else {
                        throw new BasicException("Print must have 1 parameter");
                    }
                }
            } else if (catchCommand(line, "let")) {
                evaluateLet(getParams(line, "let"));
            } else if (catchCommand(line, "quit") || catchCommand(line, "exit")) {
                stdout("Execution Halted");
                _haltExecution = true;
            } else {
                throw new BasicException("Command Not Found");
            }
        } catch (Exception e) {
            stdout("Exception while executing \"" + line + "\": " + e);
        }
    }

    private enum LetEvaluatorState {
        VAR_NAME,
        EQUALS,
        FINAL,
        FINISHED
    }

    private static void evaluateLet(String params) throws BasicException {
        String varname = "";
        String toEval = "";


        LetEvaluatorState s = LetEvaluatorState.VAR_NAME;
        for (int i = 0; i < params.length(); i++) {

            if (s != LetEvaluatorState.EQUALS && params.charAt(i) == '=') {
                throw new BasicException("Encountered and equals sign when not expected");
            }

            if (s == LetEvaluatorState.VAR_NAME) {
                if (!Character.isLetter(params.charAt(i))) {
                    s = LetEvaluatorState.EQUALS;
                } else {
                    varname += params.charAt(i);
                }
            } else if (s == LetEvaluatorState.EQUALS) {
                if (params.charAt(i) == '=') {
                    s = LetEvaluatorState.FINAL;
                }
            } else if (s == LetEvaluatorState.FINAL) {
                toEval = params.substring(i).trim();
                s = LetEvaluatorState.FINISHED;
                break;
            }
        }

        if (s == LetEvaluatorState.FINISHED) {
            set(varname.trim(), eval.calculateExpr(replaceVarsWithValues(toEval)));
        } else {
            throw new BasicException("Variable assignment failed");
        }

    }

    private static boolean containsOperator(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '^' || s.charAt(i) == '*' || s.charAt(i)=='(' || s.charAt(i) == ')') {
                return true;
            }
        }
        return false;
    }

    private static String replaceVarsWithValues(String in) throws BasicException {
        String[] parse = in.split(" ");
        String returnStr = "";
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < in.length(); i++) {
            char thisChar = in.charAt(i);
            if (Character.isLetter(thisChar)) {
                buff.append(thisChar);
            } else {
                if (buff.length() > 0) {
                    String var = buff.toString();
                    returnStr += get(var);
                    buff = new StringBuffer();
                }
                returnStr += thisChar;
            }
        }
        if (buff.length() > 0) {
            String var = buff.toString();
            returnStr += get(var);
            buff = new StringBuffer();
        }
        return returnStr;
    }

    private static Double get(String k) throws BasicException {
        if (!symbolTable.containsKey(k)) throw new BasicException("Variable \"" + k + "\" not defined");
        return symbolTable.get(k);
    }

    private static void set(String k, Double v) throws BasicException{
        if (k.toLowerCase().equals("let") || k.toLowerCase().equals("print")) {
            throw new BasicException("Keyword \"" + k + "\" is reserved.");
        }
        symbolTable.put(k,v);
    }

    private static String getParams(String in, String command) {
        return (in.length() >= command.length())? in.substring(command.length()).trim() : null;
    }

    private static boolean catchCommand(String in, String command) {
        in = in.trim();
        return (in.length() >= command.length() && in.toLowerCase().substring(0, command.length()).equals(command.toLowerCase()));
    }

    public static void stdout(String out) {
        System.out.println(out);
    }

    public static void stdout(Double out) {
        System.out.println(out);
    }

    public static String stdin(String prompt) {
        if (prompt != null) {
            System.out.print(prompt + " ");
        }
        return scanner.nextLine().trim();
    }
}



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

class BasicException extends Exception {
    public String error = "BASIC Error: ";
    public BasicException(String error) {
        this.error+=error;
    }

    @Override
    public String toString() {
        return error;
    }
}