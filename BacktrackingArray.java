
public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr = new int[]{1,2,7,15,16,23,99,100,132,193,196,197};
    private int top = arr.length-1;

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) {
        this.stack = stack;
        //arr = new int[size];
    }

    @Override
    public Integer get(int index){
        if (index>top || index<0)
            throw new NullPointerException("illegal index inserted");
        return arr[index];
    }

    @Override
    public Integer search(int k) {
        for(int i=0; i<=top;i++){
            if(arr[i]== k)
                return i;
        }
        return -1;
    }

    @Override
    public void insert(Integer x) {
        if(x==null)
            throw new IllegalArgumentException("illegal input");
        if (top== arr.length-1)
            throw new IndexOutOfBoundsException("array is full");
        top ++ ;
        arr[top]=x;
        InputStack currentValue = new InputStack(x, top, true);
        stack.push(currentValue);
    }

    @Override
    public void delete(Integer index) {
        if (top== 0)
            throw new IllegalArgumentException("array is empty");
        if (index<0 || index > top)
            throw new IllegalArgumentException("illegal index");
        InputStack currentValue = new InputStack(arr[index], index, false);
        stack.push(currentValue);
        arr[index]=arr[top];
        top--;
    }

    @Override
    public Integer minimum() {
        int min = arr[0];
        for(int i=1; i<=top ;i++){
            if(arr[i]< min)
                min = arr[i];
        }
        return min;
    }

    @Override
    public Integer maximum() {
        int max = arr[0];
        for(int i=1; i<=top;i++){
            if(arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    @Override
    public Integer successor(Integer index) {
        if(index<0 || index>top)
            throw new IndexOutOfBoundsException("illegal index");
        int myNum = arr[index];
        int dif = 0;
        int j = 0;
        boolean found = false;
        while (j <=top & !found) {
            if (arr[j] - myNum > 0) {
                dif = arr[j] - myNum;
                found = true;
            }
            j++;
        }
        j=j-1;
        for (int i = j; found && i <=top; i++) {
            int tempDif=arr[i] - myNum;
            if (i != index && tempDif>0 &&  tempDif< dif) {
                dif = tempDif;
                j = i;
            }
        }
        if (!found)
            throw new IllegalArgumentException("no successor found");
        return j;
    }



    @Override
    public Integer predecessor(Integer index) {
        if(index<0 || index>top)
            throw new IndexOutOfBoundsException("illegal index");
        int myNum = arr[index];
        int dif = 0;
        int j = 0;
        boolean found = false;
        while (j <=top & !found) {
            if (myNum-arr[j] > 0) {
                dif = myNum-arr[j];
                found = true;
            }
            j++;
        }
        j=j-1;
        for (int i = j; found && i <=top; i++) {
            int tempDif=myNum-arr[i];
            if (i != index && tempDif>0 && tempDif< dif) {
                dif = myNum-arr[i];
                j = i;
            }
        }
        if (!found)
            throw new IllegalArgumentException("no predecessor found");
        return j;
    }

    @Override
    public void backtrack() {
        if(!stack.isEmpty()){
            InputStack myBacktrack = (InputStack) stack.pop();
            boolean conditionStack = myBacktrack.actionType;
            if(conditionStack){
                top--;
            }
            else{ // reverse deletion
                int tempIndex = myBacktrack.index;
                int tempValue = arr[tempIndex];
                arr[tempIndex]= myBacktrack.value; // value pop
                top++;
                arr[top]=tempValue;
            }
        }
        else
            throw new IllegalArgumentException("no action to backtrack");

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
        BacktrackingArray missFuckingChang = new BacktrackingArray(st1, 12);
        missFuckingChang.print();
        System.out.println("\n");
//        System.out.println("max="+missFuckingChang.maximum());
//        System.out.println("min="+missFuckingChang.minimum());
//        System.out.println(missFuckingChang.arr[4]);
//        System.out.println("pred= "+missFuckingChang.predecessor(0));
//        System.out.println("suc="+missFuckingChang.successor(0));
        missFuckingChang.delete(8);
        System.out.println("top_without132= "+missFuckingChang.top);
        missFuckingChang.print();
        System.out.println("\n");
        missFuckingChang.insert(200);
        missFuckingChang.print();
        System.out.println("top_with200= "+missFuckingChang.top);
        missFuckingChang.backtrack();
        System.out.println("backtrack 200: ");
        missFuckingChang.print();
        System.out.println("top_without200= "+missFuckingChang.top);
        missFuckingChang.backtrack();
        System.out.println("top_with_132="+missFuckingChang.top);
        missFuckingChang.print();
//        missFuckingChang.delete(11);
//        System.out.println("top2= "+missFuckingChang.top);
        System.out.println("\n");
//        missFuckingChang.print();
//        System.out.println("\n");
////        System.out.println("max2= "+missFuckingChang.maximum());
//        missFuckingChang.backtrack();
//        System.out.println("top3= "+missFuckingChang.top);
//        missFuckingChang.print();
//       System.out.println("\n");
//        missFuckingChang.backtrack();
//        System.out.println("top4= "+missFuckingChang.top);
////        System.out.println("\n");
//        missFuckingChang.print();




    }
}
