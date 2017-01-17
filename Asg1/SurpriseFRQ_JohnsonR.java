class DiverseArray {
    
    public static int arraySum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }
    
    public static int[] rowSums(int[][]arr2D) {
        int[] rowsum = new int[arr2D.length];
        for (int i = 0; i < arr2D.length; i++){
            rowsum[i] = arraySum(arr2D[i]);
        }
        return rowsum;
    }
    
    public static boolean isDiverse(int[][] arr2D) {
        int[] sums = DiverseArray.rowSums(arr2D);
        for (int i = 0; i < sums.length; i++){
            for (int j = 0; j < sums.length; j++){
                if (sums[i] == sums[j] && i != j) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class SurpriseFRQ_JohnsonR {
    public static void main(String[] args){
        int[][] test = {{1,2,3,4,5,6,7,8,9,10}, {1,2,3,4,5,6,7,8,9, 10}};
        //printArr(DiverseArray.rowSums(test));
        System.out.println(DiverseArray.isDiverse(test));
        
    }
    
    public static void printArr(int[] in) {
        System.out.print("{");
        for (int i = 0; i < in.length; i++){
            System.out.print(in[i] + ", ");
        }
        System.out.println("}");
        
    }
}