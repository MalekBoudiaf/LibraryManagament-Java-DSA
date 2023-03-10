package datastructures;

public class Queue {
	
	private LinkedList data;
	
	public Queue() {
		this.data=new LinkedList();
	}

	public void push(Object o) {
		data.addLast((Comparable) o);
	}

	public Object pop() {
		Object temp=data.getFirst();
		data.removeFirst();
		return temp;
	}

	public Object top() {
		return data.getFirst();
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
