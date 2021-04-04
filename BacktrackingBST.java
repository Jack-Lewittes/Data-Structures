import java.util.NoSuchElementException;

public class
 BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;

    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
    }

    public Node getRoot() {
    	if (root == null) {
    		throw new NoSuchElementException("empty tree has no root");
    	}
        return root;
    }
	
    public Node search(int k) {
        if(root!=null){
            BacktrackingBST.Node curr = root;
            boolean found = false;
            while(curr != null){
                if (k < (Integer)curr.value)
                    curr = curr.left;
                else if (k > (Integer)curr.value){
                    curr = curr.right;
                }
                else{
                    return curr;
                }
            }//while
        }//if
        return null;
    }

    public void insert(Node node) {
        if(node==null)
            throw new NullPointerException("the input value is null");
        BacktrackingBST.Node father=null;
        BacktrackingBST.Node curr=root;
        while(curr!=null){
            father=curr;
            if(node.key<curr.key)
                curr=curr.left;
            else
                curr=curr.right;
        }
        node.parent=father;
        if(father==null)
            root=node;
        else if(node.key<father.key)
            father.left=node;
        else
            father.right=node;
        InputBSTStack currInput=new InputBSTStack(node,1,false);
        stack.push(currInput);
    }

    public void delete(Node node) {
        if(search(node.key)!=null){
            BacktrackingBST.Node temp;
            InputBSTStack currInput;
            if(node.left==null & node.right==null){
                temp=node.parent;
                if(temp.right==node) {
                    currInput = new InputBSTStack(node, 0, false);
                    stack.push(currInput);
                    delete(temp.right);
                }
                else {
                    currInput = new InputBSTStack(node, 0, false);
                    stack.push(currInput);
                    delete(temp.left);
                }
            }else if(node.left==null || node.right==null) {
                if (node.left != null) {
                    temp=node.left;
                    currInput=new InputBSTStack(node,0,false);
                    stack.push(currInput);
                    delete(temp);
                    node.key = temp.key;
                } else {
                    temp=node.right;
                    currInput=new InputBSTStack(node,0,false);
                    stack.push(currInput);
                    delete(temp);
                    node.key = temp.key;
                }
            }
            else{
                BacktrackingBST.Node value= findLocalMin(node.right);
                currInput=new InputBSTStack(node,0,true);
                stack.push(currInput);
                delete(value);
                node.key=value.key;
                }
        }
    }

    public BacktrackingBST.Node findLocalMin(Node node){                //aid function
        BacktrackingBST.Node curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public Node minimum() {
        if (root == null)
            throw new NoSuchElementException("empty tree has no root");
        BacktrackingBST.Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;

    }

    public Node maximum() {
        if (root == null)
            throw new NoSuchElementException("empty tree has no root");
        BacktrackingBST.Node curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    public Node successor(Node node) {
        if (node.right ==null || node==maximum())
            throw new IllegalArgumentException("no successor found");
        if(search(node.key)==null)
            throw  new IllegalArgumentException("the node is not in the tree");
        BacktrackingBST.Node curr;
        if(node.right!=null){
            curr=findLocalMin(node.right);
            return curr;
        }
        curr=node.parent;
        Node temp=node;
        while (curr!=null && temp.value==curr.right.value){
            temp=curr;
            curr=curr.parent;
        }
        return curr;
    }

    public Node predecessor(Node node) {
        if(node.left==null || node==minimum())
            throw new IllegalArgumentException("no predecessor found");
        if(search(node.key)==null)
            throw  new IllegalArgumentException("the node is not in the tree");
        BacktrackingBST.Node curr;
        if(node.left!=null){
            curr=node.left;
            while ((curr.right!=null)){
                curr=curr.right;
            }
            return curr;
        }
        curr=node.parent;
        Node temp=node;
        while (curr!=null && temp.value==curr.left.value){
            temp=curr;
            curr=curr.parent;
        }
        return curr;
    }

    @Override
    public void backtrack() {
        // TODO: implement your code here
    }

    @Override
    public void retrack() {
        // TODO: implement your code here
    }

    @Override
    public void print() {
    	printPreOrder();
    }


    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node curr){ // aid Function
        System.out.print(curr.value);
        if (curr.left!=null) {
            System.out.print(" ");
            printPreOrder(curr.left);
        }
        if (curr.right!=null) {
            System.out.print(" ");
            printPreOrder(curr.right);
        }
    }


    public static class Node {
    	// These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;
        
        private BacktrackingBST.Node parent;
        private int key;
        private Object value;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
        
    }

    public static void main (String[] args){
        Stack st1=new Stack();
        Stack st2=new Stack();
        BacktrackingBST michal=new BacktrackingBST(st1,st2);
        BacktrackingBST.Node curr =new BacktrackingBST.Node(0,12);
        michal.insert(curr);
        BacktrackingBST.Node curr2=new BacktrackingBST.Node(1,6);
        michal.insert(curr2);
        michal.print();
        System.out.println("\n");
    }

}
