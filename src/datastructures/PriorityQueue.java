package datastructures;
import java.util.Comparator;

public class PriorityQueue 
{       
	private class PriorityPair implements Comparable {
		private Object element;
		private Object priority;

		public PriorityPair(Object element, Object priority) {
			this.element = element;
			this.priority = priority;
		}

		public int compareTo(Object o) {
			PriorityPair p2 = (PriorityPair) o;
			return ((Comparable) priority).compareTo(p2.priority);
		}
	}

	private SortedLinkedList data;

	public PriorityQueue()
	{
		data = new SortedLinkedList();
	}

	public void push(Object o, int priority)
	{
		PriorityPair pair=new PriorityPair(o,priority);
		data.addSorted(pair);
	}

	public Object pop()
	{
		Comparable temp=data.getFirst();
		data.removeFirst();
		return ((PriorityPair)temp).element;
	}

	public Object top()
	{
		return ((PriorityPair)data.getFirst()).element;
	}

	public boolean isEmpty(){return data.isEmpty();}
}
