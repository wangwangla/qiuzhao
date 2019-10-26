package kw.test;

import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class ArrayList<T> {
	//存储数据
	private transient Object[] elementData;
	//大小size
	private int size;
	//指定大小
	public ArrayList(int initialCapacity) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
        this.elementData = new Object[initialCapacity];
    }
	//默认大小
    public ArrayList() {
        this(10);
    }
	//grow 
    private void grow(int minCapacity){
    	int oldCapacity = elementData.length;
    	//扩展为1.5倍        2 + 4 
    	int newCapacity = oldCapacity + (oldCapacity >> 1);
    	if(newCapacity - minCapacity<0){
    		newCapacity  = minCapacity;
    	}
    	if(newCapacity - (Integer.MAX_VALUE - 8)>0){
    		newCapacity = Integer.MAX_VALUE;
    	}
    	//this.elementData = new Object[newCapacity];
    	this.elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    public int size(){
    	return size;
    }
    
    public boolean isEmtry(){
    	return this.size == 0;
    }
    
    // null和一般字符串的比较方式是不一样的
    public int indexOf(Object o){
    	if(o == null){
    		for(int i=0;i<size;i++){
    			if(elementData[i] == null){
    				return i;
    			}
    		}
    	}else{
    		for(int i = 0;i<size;i++){
    			if(o.equals(elementData[i]));
    			return i;
    		}
    	}
    	return -1;
    }
  
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    
}
