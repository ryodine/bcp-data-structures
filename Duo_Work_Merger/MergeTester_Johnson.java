import java.util.ArrayList;


class MergeTester_Johnson {
    public static void main(String[] args) {
        int[] array1 = merge(genArray(), genArray());
        for (int term : array1) {
            System.out.print(term + ",");
        }
    }
    
    /**
     * Generates Array with values from 0-10,000 with random length from 900-1100
     * @return int[]
     */
    public static int[] genArray() {
        int length = (int)(Math.random()*200) + 900; //NO MAGIC NUMBERS, just generate a random length in the range
        int[] random = new int[length];
        
        int last = 0;
        
        for (int i = 0; i < length; i++) {
            //Create a scaled max value for term based on previous values, creates logarithmic curve.
            double scaling = ((i==0)? 0.0 : (10000.0-last));
            
            //Create a scaling cap to preserve near-linearity of random numbers, based on probability of a random number
            double scalingCap = (10000.0/length * 2.0);
            
            //set the current scale so that it does not become nonlinear
            scaling = (scaling > scalingCap)? scalingCap : scaling;
            
            
            int r = (int)(Math.random() * scaling);
            last+=r;
            random[i] = last;
        }
        
        return random;
    }
    
    
    /**
     * Mergesort 2 int[] arrays
     * @param int[] a
     * @param int[] b
     * @return int[]
     */
    public static int[] merge(int[] a, int[] b) {
    
        int[] answer = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
    
        while (i < a.length && j < b.length)
        {
            if (a[i] < b[j])       
                answer[k++] = a[i++];
    
            else        
                answer[k++] = b[j++];               
        }
    
        while (i < a.length)  
            answer[k++] = a[i++];
    
    
        while (j < b.length)    
            answer[k++] = b[j++];
    
        return answer;
    }
}