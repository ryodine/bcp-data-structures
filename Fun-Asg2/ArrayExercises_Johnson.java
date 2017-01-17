class ArrayExercises_Johnson {
    public static void main(String[] args) {
        System.out.println("Results of fill array");
        int[] arr = fillArray();
        printArray(arr);
        minmax(arr);
        System.out.println("LONGEST RUN: " + run(arr));
        System.out.println("Question 28 on 5x5 grid: ");
        print2D(question28(5, 5));
        System.out.println("Magic Square:");
        print2D(magicsquare(5));
        int[] x = {-4, -1, 1, 4, 5};
        barchart(x);
    }
    
    public static int[] fillArray() {
        int[] values = new int[10];
        for (int i = 0; i < 10; i++) {
            int value = (int) (Math.random() * 99 + 1);
            for (int j = 0; j < i; j++) {
                if (value == values[j]) {
                    value = (int) Math.random() * 99 + 1;
                    j = 0;
                }
            }
            values[i] = value;
        }
        return values;
    }
    
    public static void minmax(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("MIN: " + min + " MAX: " + max);
    }
    
    public static int run(int[] arr) {
        int count = 0;
        int last = arr[0] + 1;
        for (int i = 0; i < arr.length; i++) {
            if (last == arr[i]) {
                count++;
            }   
            last = arr[i];
        }
        return count;
    }
    
    public static int[][] question28(int r, int c) {
        int[][] arr = new int[r][c];
        for(int i = 0; i < arr.length; i++) {
            int val = 1;
            if (i == 0 || i == arr.length - 1) {
                val = 0;
            }
            for(int j = 0; j < arr[i].length; j++) {   
                arr[i][j] = val;
            }
        }
        return arr;
    }
    
    public static int[][] magicsquare(int n) {
        int[][] arr = new int[n][n];
        if (n%2 == 0) {
            System.out.println("PARAMETER n MUST BE ODD");
            return null;
        } else {
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++) {
                    arr[i][j] = -1;
                }
            }
            int row = n-1;
            int orow = 0;
            int column = n/2;
            int ocol = 0;
            for (int i = 1; i <=n*n; i++){
                orow = row;
                ocol = column;
                
                arr[row][column] = i;
                row++; column++;
                if (row == n) {
                    row = 0;
                }
                if (column == n) {
                    column = 0;
                }
                if (arr[row][column] != -1) {
                    row = orow;
                    column = ocol;
                    row--;
                }
            }
        }
        return arr;
    }
    
    public static void barchart(int[] chart) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chart.length; i++) {
            if (chart[i] > max) {
                max = chart[i];
            }
            if (chart[i] < min) {
                min = chart[i];
            }
        }
        
        int unitSize = (int) 40/(max-min);
        
        int medianCharPos = Math.abs(min) * unitSize;
        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < 40; j++){
                if (j != medianCharPos) {
                    if (j == medianCharPos+(unitSize*chart[i])) {
                        System.out.print("*");
                    } else {
                        System.out.print("-");
                    }
                } else {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        
    }
    
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ((i != arr.length-1)? ", " : ""));
        }
        System.out.println("]");
    }
    public static void print2D(int[][] arr){
          for(int i  = 0; i < arr.length; i++){
               if(i != 0){System.out.print("\n");}
               for(int j = 0; j < arr[i].length; j++) {
                    System.out.printf("%3d", arr[i][j]);
               }
          }
          System.out.print("\n");
    }
}