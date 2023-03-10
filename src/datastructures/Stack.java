package datastructures;

public class Stack {
	
	private Vector data;

	public Stack() {
		this.data=new Vector();
	}

	public void push(Object o) {
		data.addLast(o);
	}

	public Object pop() {
		Object temp=data.getLast();
		data.removeLast();
		return temp;
	}

	public Object top() {
		return data.getLast();
	}

	public int size() {
		return data.size();
	}

	public boolean empty() {
		return data.isEmpty();
	}

	@Override
	public String toString() {
      return data.toString();
	}
}
