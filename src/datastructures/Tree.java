package datastructures;
import java.util.Comparator;


public class Tree {
	/*
	private class NaturalComparator implements Comparator
	{
		public int compare(Object a, Object b)
		{
			return ((Comparable)a).compareTo(b);
		}
	}
	*/
	// the class for implementing a node in the tree.
	// contains a value, a pointer to the left child and a pointer to the right child
	
	public class TreeNode implements Comparable
	{
	 private Comparable value;
	 private TreeNode leftNode;
	 private TreeNode rightNode;
	 private TreeNode parent;
	 public TreeNode(Comparable v)
	 {
	  value = v;
	  leftNode = null;
	  rightNode = null;
	 }
	  
	 public TreeNode(Comparable v, TreeNode left, TreeNode right)
	 {
	  value = v;
	  leftNode = left;
	  rightNode = right;
	 }
	 public TreeNode getLeftTree()
	 {
	  return leftNode;
	 }
	 
	 public TreeNode getRightTree()
	 {
	  return rightNode;
	 }
	 
	 
	 public Comparable getValue()
	 {
		 return value;
	 }

	 public TreeNode getParent()
		{
			return this.parent;
		}

		public void setLeftNode(TreeNode leftNode) {
			this.leftNode = leftNode;
		}

		public void setRightNode(TreeNode rightNode) {
			this.rightNode = rightNode;
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	 

	}


	private class MutableString{
		private String s="";

		public void append(String str){
			this.s+=str;
		}
		public String getString(){
			return this.s;
		}

	}
	
	/*public static abstract class TreeAction
	{
		public abstract void run(TreeNode n);
	}*/

		
	// start of the actual tree class
	
	// the root of our tree
	protected TreeNode root;
	
	public Tree()
	{
		 root = null;
	}

	public Comparable find(Comparable value){
          return findNode(value,root);
	}

	private Comparable findNode(Comparable value,TreeNode current){
		if(current==null){return null;}
		else if(current.value.compareTo(value)==0){return current.value;}
		else if(current.value.compareTo(value)>0){return findNode(value,current.getLeftTree());}
		else{return findNode(value,current.getRightTree());}
	}
	
	
	public void traverse(TreeAction action)
	{
		Queue t = new Queue();
//		//Stack t = new Stack();
		if(root!=null){t.push(root);}
		while(!t.empty())
		{
			TreeNode n = (TreeNode)t.pop();
			if (n!=null){
			action.run(n);
			}

			if(n.getLeftTree() != null) t.push(n.getLeftTree());
			if(n.getRightTree() != null) t.push(n.getRightTree());
		}
	}
	
	public void traverseNode(TreeNode n,TreeAction action)
	{
		if(n != null)
		{
			if(n.getLeftTree() != null) traverseNode(n.getLeftTree(),action); 
			action.run(n);
			if(n.getRightTree() != null) traverseNode(n.getRightTree(),action);
		}
	}
	
	public void traverseInOrder(TreeAction action)
	{
		traverseNode(root,action);
	} 
	
	public void insert(Comparable element)
	{
		insertAtNode(element,root,null);
	}	
	
	// we traverse the tree.
	// Current holds the pointer to the TreeNode we are currently checking
	// Parent holds the pointer to the parent of the current TreeNode
	private void insertAtNode(Comparable element,TreeNode current,TreeNode parent)
	{
		// if the node we check is empty
		if(current == null)
		{
			TreeNode newNode = new TreeNode(element);
			// the current node is empty, but we have a parent
			if(parent != null)
			{
				// do we add it to the left?
				if(element.compareTo(parent.value) < 0)
				{
					parent.leftNode = newNode;
				}
				// or do we add it to the right?
				else
				{
					parent.rightNode = newNode;
				}

			}
			// the current node is empty and it has no parent, we actually have an empty tree
			else root = newNode;
			newNode.parent=parent;
		}
		else if(element.compareTo(current.value) == 0)
		{
			// if the element is already in the tree, what to do?
		}
		// if the element is smaller than current, go left
		else if(element.compareTo(current.value) < 0)
		{
			insertAtNode(element,current.getLeftTree(),current);
		}
		// if the element is bigger than current, go right
		else insertAtNode(element,current.getRightTree(),current);
	}



	public String toString ()
	{
		MutableString s=new MutableString();
		traverse(new TreeAction() {
			@Override
			public void run(TreeNode n) {
				s.append(n.value.toString());
				s.append("\n");
			}
		});
		return s.getString();
	}
	
	public void myTraverse(){
		myTraverseInternal(root);
	}
	private void myTraverseInternal(TreeNode current){
		if(current==null){return;}
		System.out.println(current.value.toString());
		myTraverseInternal(current.getLeftTree());
		myTraverseInternal(current.getRightTree());
	}

	public int getHeight(){
		return getHeightInternal(root);
	}

	private int getHeightInternal(TreeNode root){
		if(root==null){return -1;}
		int leftHeight=getHeightInternal(root.getLeftTree());
		int rightHeight=getHeightInternal(root.getRightTree());
		return Math.max(leftHeight,rightHeight)+1;
	}

	public Comparable getBiggestElement(){
		return getBiggestInternal(root);
	}
	private Comparable getBiggestInternal(TreeNode current){
		if(current.getRightTree()==null){return current.value;}
		return getBiggestInternal(current.getRightTree());
	}

	public void remove(Comparable element){
           removeNode(element,root);
	}
	private void removeNode(Comparable element,TreeNode current){

		if(current==null){return;}
		else if(element.compareTo(current.getValue())==0){
				if (current.getRightTree()==null){
					transplant(current,current.getLeftTree());
				}else if(current.getLeftTree()==null){
					transplant(current,current.getRightTree());
				}else{
					TreeNode y=minNode(current.getRightTree());
					if (y.getParent()!=current){
						transplant(y,y.getRightTree());
						y.rightNode=current.getRightTree();
						y.getRightTree().parent=y;
					}
					transplant(current,y);
					y.leftNode=current.getLeftTree();
					y.getLeftTree().parent=y;
				}

		}else if(element.compareTo(current.getValue())>0){removeNode(element,current.getRightTree());}
		else{removeNode(element,current.getLeftTree());}
	}

	private TreeNode minNode(TreeNode current){
		if(current==null){ return null;}
		else if(current.getLeftTree()==null){return current;}
		else{return minNode(current.getLeftTree());}
	}

	private void transplant(TreeNode oldNode,TreeNode newNode){
         if(oldNode.getParent()==null){root=newNode;}
		 else if(oldNode.getParent().getRightTree()==oldNode){oldNode.getParent().rightNode=newNode;}
		 else{oldNode.getParent().leftNode=newNode;}

		 if(newNode!=null){newNode.parent=oldNode.parent;}
	}



	
}

