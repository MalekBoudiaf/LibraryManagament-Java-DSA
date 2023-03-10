package datastructures;

public class Vector {
    private Object[] data;
    private int count;


    public Vector() {
        data = new Object[10];
        count = 0;

    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Object get(int index) {
        return data[index];
    }

    public void set(int index, Object obj) {
        data[index] = obj;
    }

    public boolean contains(Object obj) {
        for (int i = 0; i < count; i++) {
            if (data[i] == obj) return true;
        }
        return false;
    }

    public void addFirst(Object item) {
        if(count>=data.length){
            extendCapacity();
        }
        for (int i=count-1;i>=0;i--){
            data[i+1]=data[i];
        }
        data[0]=item;
        count++;
    }

    public void addLast(Object o) {
		if(count>= data.length){
          extendCapacity();
        }
        data[count] = o;
        count++;
    }
	

	public Comparable binarySearch(Comparable item)
	{
	int start = 0;
	int end = count - 1;
	while(start <= end)
	{
		int middle = (start + end + 1) / 2;
		if(item.compareTo(data[middle])<0) end = middle -1;
		else if(item.compareTo(data[middle])>0) start = middle + 1;
		else return (Comparable) data[middle];
	}
	return null;
	}


    public Object getFirst() {
        if (count!=0){return data[0];}
        else{return null;}
    }

    public Object getLast() {
        if(count!=0) return data[count-1];
        else return null;
    }

    public void removeLast() {
        if(!isEmpty()){
            data[count-1]=null;
            count--;
        }
    }

    public void removeFirst() {
        if(!isEmpty()){
            for(int i=1;i<count;i++){
               data[i-1]=data[i];
            }
            data[count-1]=null;
            count--;
        }
    }
    public void remove(int index){
        if(index<count){
            if (index==count-1){
                removeLast();
            } else if(index==0){
                removeFirst();
            } else{
               for(int i=index;i<count-1;i++){
                   data[i]=data[i+1];
               }
               data[count-1]=null;
               count--;
            }
        }
    }

    @Override
    public String toString() {
        String s="";
        for (int i=0;i<count;i++){
            s=s+data[i].toString();
            s+="\n";
        }
        return s;
    }

    public void reverse(){
        int half=count/2;
        for(int i=0;i<half;i++){
            Object temp=data[count-1-i];
            data[count-1-i]=data[i];
            data[i]=temp;
        }
    }
    public Vector repeat(){
        Vector v=new Vector();
        for(int i=0;i<count;i++){
            v.addLast(data[i]);
            v.addLast(data[i]);
        }
        return v;
    }
    /*public Vector interleave(Vector v2){
        Vector v_result=new Vector(size()+v2.size()+50);
        int result_size=size()+v2.size();
        int v1_count=0;
        int v2_count=0;
        for (int i=0;i<result_size;i++){
            if(v1_count<size()){
                v_result.addLast(get(v1_count));
                v1_count++;
            }

            if(v2_count<v2.size()){
                v_result.addLast(v2.get(v2_count));
                v2_count++;
            }

        }
        return v_result;
    }*/

    private void extendCapacity(){
        Object[] data2=new Object[data.length*2];
        for(int i=0;i<count;i++){
            data2[i]=data[i];
        }
        data=data2;
    }

}