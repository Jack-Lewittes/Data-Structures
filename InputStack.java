public class InputStack {
    // Class created to save multiple fields in one object for Stack objects (for backtracking purposes in array dataframe)
    protected int value;
    protected int index;
    protected boolean actionType;   // false = delete, true= insert

    public InputStack (int value, int index, boolean action) {
        this.value = value;
        this.index = index;
        this.actionType = action;
    }
}

