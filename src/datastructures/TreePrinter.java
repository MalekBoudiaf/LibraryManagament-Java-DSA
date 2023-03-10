package datastructures;

public class TreePrinter extends TreeAction {
    @Override
    public void run(Tree.TreeNode n) {
        System.out.println(n.getValue().toString());
    }
}
