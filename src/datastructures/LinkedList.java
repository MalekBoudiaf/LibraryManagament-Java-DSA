package datastructures;

public class LinkedList {
	
	private class ListElement {
		private Comparable el1;
		private ListElement el2;

		public ListElement(Comparable el, ListElement nextElement) {
			el1 = el;
			el2 = nextElement;
		}

		public ListElement(Comparable el) {
			this(el, null);
		}

		public Comparable data() {
			return el1;
		}

		public ListElement next() {
			return el2;
		}

		public void setData(Comparable value) {
			el1 = value;
		}

		public void setNext(ListElement value) {
			el2 = value;
		}
	}
	
	private ListElement head;

	public LinkedList() {
		head = null;
	}

	public void addFirst(Comparable o) {
		head = new ListElement(o, head);
	}


	public Comparable GetBiggest(){

		if(head!=null) {

			if(head.next()==null){
				return head.data();
			}else{

				ListElement biggest = head;
				ListElement current = head;

				while (current.next() != null) {
					if (current.next().data().compareTo(biggest.data()) < 0) {
						biggest = current.next();
					}
					current = current.next();
				}
				return biggest.data();
			}
		}else{
			return null;
		}

	}


	public void removeFirst(){
		if(head!=null) head=head.next();
	}

	public Comparable getFirst() {

		if(head!=null) return head.data();
		else {
			return null;
		}
	}

	public Comparable get(int n) {
		ListElement d = head;
		while (n > 0) {
			d = d.next();
			n--;
		}
		return d.data();
	}
	
	public String toString() {
		String s = "";
		ListElement d = head;
		while (d != null) {
			s += d.data().toString();
			s += "\n";
			d = d.next();
		}
		return s;
	}

	public int size(){
		int size=0;
		ListElement temp=head;
		while(temp!=null){
			temp=temp.next();
			size++;
		}
		return size;
	}
	public boolean isEmpty(){
		return size()==0;
	}

	public void set(int n,Comparable o){
		ListElement temp=head;
		if (n>=size()){
			System.out.println("No existing element at this position (out of bound)");
		}else{
		while(n>0){
			temp=temp.next();
			n--;
		}
		temp.setData(o);
		}
	}

    public Comparable getLast(){
		ListElement temp=head;
		if(head!=null) {
			while (temp.next() != null) {
				temp = temp.next();
			}
			return temp.data();
		}else{return null;}
	}
	public void addLast(Comparable o){
        ListElement temp=head;
		if(temp==null){
			addFirst(o);
		}else{
		while(temp.next()!=null){
			temp=temp.next();
		}
		temp.setNext(new ListElement(o));
		}
	}
	public boolean contains(Comparable o){
		ListElement temp=head;
		while(temp!=null){
			if (temp.data()==o){
				return true;
			}
			temp=temp.next();
		}
		return false;
	}

	public int search(Comparable o){
		ListElement temp=head;
		int index=0;
		while(temp!=null){
			if (temp.data()==o){
				return index;
			}
			index++;
			temp=temp.next();
		}
		return -1;
	}


}
