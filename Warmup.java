

public class Warmup {
    public static int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack) {
        int counter = 1;
        int size = arr.length;
        int output = -1;
        boolean found = false ;
        for (int i =0; i < size & !found; i++){
            myStack.push(arr[i]);
            if (x == arr[i]){
                output = i;
                found = true ;
            }//if
            counter++;
            if (counter == forward){
                for(int k = back; k>0 ; k--){
                    myStack.pop();
                }//for
                i=(i-back)+1; // plus one to compensate for initial difference of i and counter (index begins with 0)
                counter= 1;
            }//if
        }//for
       return output;
        }

    public static int consistentBinSearch(int[] arr, int x, Stack myStack) {

        int high = arr.length-1;
        int low = 0;
        while (low <= high) {
            int middle = (low+high)/2;
            myStack.push(arr[middle]);
            if (arr[middle]==x)
                return middle;

            int inconsistencies = Consistency.isConsistent(arr);
            if(inconsistencies!=0) {
                for(int i=0; i<inconsistencies; i=i+1) // backtracking according to consistency value
                    if(!myStack.isEmpty())
                        myStack.pop();
            }
            if (arr[middle]>x)	{
                high =middle-1;
            }
            else  {
                low = middle+1;
            }
        }
        return -1;
    }

    public static void main (String[] args){
        int[] arr1= new int[] {1, 1, 13, 14, 15, 16, 23, 99, 100, 100, 100, 132, 193, 196, 197};
        Stack myS = new Stack();
        int good = consistentBinSearch(arr1, 132, myS);
        int bad = consistentBinSearch(arr1, 134555, myS);
        System.out.println("good="+ good+ ", bad="+bad);

    }
}
// internal design