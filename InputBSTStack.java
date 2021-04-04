public class InputBSTStack {
    // Class created to save multiple fields in one object for Stack objects (for backtracking purposes in BST)
    protected BacktrackingBST.Node node;
    protected int action;
    protected boolean hasTwoKids;

    public InputBSTStack(BacktrackingBST.Node node, int action, boolean hasTwoKids) {
        this.node=node;
        this.action=action; // 0-delete, 1-insert
        this.hasTwoKids = hasTwoKids;
    }
}

