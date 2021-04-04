

public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    public int[] arr= new int[]{1,2,7,15,16,23,99,100,132,193,196,197}; // This field is public for grading purposes. By coding conventions and best practice it should be private.
    private int top=  arr.length-1;

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
       // arr = new int[size];

    }
    
    @Override
    public Integer get(int index){
        if (index>top || index<0)
            throw new NullPointerException("illegal index inserted");
        return arr[index];
    }

    @Override
    public Integer search(int k) {
        int low = 0;
        int high = top;
        while (low<=high){
            int middle = (low+high)/2;
            if (k == arr[middle])
                return middle;
            else if (k < arr[middle])
                high = middle -1;
            else
                low = middle +1;
            }
        return -1;
        }

    @Override
    public void insert(Integer x) {
        if(x==null)
            throw new IllegalArgumentException("illegal value inserted");
        if (top == arr.length-1)
            throw new IllegalArgumentException("array is full");
        int middle = arr[top/2];
        int insertIndex;
        if (x<middle){
            insertIndex = 0;
            while (insertIndex<top/2 && x > arr[insertIndex])
                insertIndex++;
        }
        else{
            insertIndex = top/2;
            while (insertIndex<=top && x < arr[insertIndex])
                insertIndex++;
        }
        shiftRight(insertIndex, x);
    }

    private void shiftRight(int index, Integer value){
        if(index>top)
            throw new IndexOutOfBoundsException("index out of array size");
        int temp1 = arr[index];
        int temp2 = value;
        top++;
        for (int i =index; i<top; i++) { //swap values
            if (arr[i] > value) {
                arr[i] = temp2;
                temp2 = temp1;
                temp1 = arr[i + 1];
            }
            else{
                if (i+1==top) {
                    arr[i + 1] = value;
                    index = i + 1; // final index of value
                }
                else
                    temp1=arr[i+1];
            }
        }
        arr[top]=temp2;
        InputStack currentValue = new InputStack(value,index, true);
        stack.push(currentValue);
    }

    @Override
    public void delete(Integer index) {
        if (index>top | index < 0)
            throw new IllegalArgumentException("illegal index");
        InputStack currentValue = new InputStack(arr[index], index, false);
        stack.push(currentValue);
        shiftLeft(index);
    }

    private void shiftLeft (Integer index) {
        if (index > top)
            throw new IndexOutOfBoundsException("index out of array size");
        if (index < top) {
            int temp1;
            for (int i = index + 1; i < top; i++) {
                temp1 = arr[i];
                arr[i - 1] = temp1;
            }
            arr[top - 1] = arr[top];
        }
        top--;
    }

    @Override
    public Integer minimum() {
        if (top< 0)
            throw new NullPointerException("array is empty");
        return 0;
    }

    @Override
    public Integer maximum() {
        if (top > arr.length-1)
            throw new NullPointerException("array is empty");
        return top;
    }

    @Override
    public Integer successor(Integer index) {
        if (index > top || index<0)
            throw new NullPointerException("illegal index inserted");
        return index+1;
    }

    @Override
    public Integer predecessor(Integer index) {
        if (index>top || index<0)
            throw new NullPointerException("illegal index inserted");
        return index-1;
    }

    @Override
    public void backtrack() {
        if (stack.isEmpty()){
           throw new IllegalArgumentException("no backtrack available");
        }
        InputStack myBacktrack = (InputStack)stack.pop();
        if (myBacktrack.actionType){ // previous action was insert
            int index = myBacktrack.index;
            shiftLeft(index);
        }
        else{// previous action was delete
            int index = myBacktrack.index;
            int value= myBacktrack.value;
            shiftRight(index, value);
        }
    }

    @Override
    public void retrack() {
		/////////////////////////////////////
		// Do not implement anything here! //
		/////////////////////////////////////
    }

    @Override
    public void print() {
        String output="";
        for(int i = 0 ; i <= top ; i++){
            output=output+arr[i]+" ";
        }
        System.out.println(output.substring(0,output.length()-1));
    }


    public static void main (String[] args){
        Stack st1 = new Stack();
        BacktrackingSortedArray missFuckingChang = new BacktrackingSortedArray(st1, 12);
        missFuckingChang.print();
        System.out.println("\n");
//        System.out.println("max="+missFuckingChang.maximum());
//        System.out.println("min="+missFuckingChang.minimum());
//        System.out.println(missFuckingChang.arr[4]);
//        System.out.println("pred= "+missFuckingChang.predecessor(4));
//        System.out.println("suc="+missFuckingChang.successor(4));
        missFuckingChang.delete(8);
        missFuckingChang.print();
        missFuckingChang.insert(200);
        missFuckingChang.print();
        System.out.println("top_with200= "+missFuckingChang.top);
        missFuckingChang.backtrack();
        System.out.println("backtrack 200: ");
        missFuckingChang.print();
        System.out.println("top_without200= "+missFuckingChang.top);

////        missFuckingChang.print();
////        System.out.println("\n");
//        //missFuckingChang.delete(0);
//        //System.out.println("top2= "+missFuckingChang.top);
//        System.out.println("\n");
////        System.out.println("min2= "+missFuckingChang.arr[missFuckingChang.minimum()]);
        missFuckingChang.backtrack();
        System.out.println("top_with_132= "+missFuckingChang.top);
        missFuckingChang.print();
        System.out.println("\n");
//        missFuckingChang.backtrack();
//        System.out.println("top4= "+missFuckingChang.top);
//        System.out.println("\n");
//        missFuckingChang.print();




    }
    
}
