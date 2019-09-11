package com.test.array;

/**
 *	 数组
 * @author Administrator
 *
 * @param <T>
 */
public class GenericArray<T> {
	private T[] data;
	private int size;
	public GenericArray(int capacity){
		data =(T[]) new Object[capacity];
		size = 0;
	}
	
	public GenericArray() {
		this(10);
	}
	
	public int getCapacity() {
		return data.length;
	}
	
	private int count() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0 ? true:false;
	}
	
	public void set(int index,T t) {
		data[index] = t;
	}
	
	public T get(int index) {
		return data[index];
	}
	
	public boolean contains(T e) {
		for(int i=0;i<size;i++) {
			if(data[i].equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	public int find(T e) {
		for(int i=0;i<size;i++) {
			if(data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
	 // 在 index 位置，插入元素e, 时间复杂度 O(m+n)
    public void add(int index, T e) {
    	if(index<0||index>data.length) {
    		throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
    	}
    	if(data.length-1==size) {
    		throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
    	}
    	for(int i=size;i>index;i--) {
    		data[i]=data[i-1];
    	}
    	size++;
    }

    // 向数组头插入元素
    public void addFirst(T e) {
    	if(data.length-1==size) {
    		throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
    	}
    	for(int i=size;i>0;i--) {
    		data[i]=data[i-1];
    	}
    	size++;
    }

    // 向数组尾插入元素
    public void addLast(T e) {
    	if(data.length-1==size) {
    		throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
    	}
    	data[++size]=e;
    }

    // 删除 index 位置的元素，并返回
    public T remove(int index) {
    	T t = data[index];
    	for(int i=index ; i<size;i--) {
    		data[i]=data[i+1];
    	}
    	size--;
		return t;
    }

    // 删除第一个元素
    public T removeFirst() {
    	T t = data[0];
    	for(int i=0;i<size-1;i++) {
    		data[i]=data[i+1];
    	}
    	size--;
		return t;
    }

    // 删除末尾元素
    public T removeLast() {
    	T t = data[size-1];
    	size--;
		return t;
    }

    // 从数组中删除指定元素
    public void removeElement(T e) {
    	int index = find(e);
    	remove(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }


    // 扩容方法，时间复杂度 O(n)
    private void resize(int capacity) {

    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }
    
    private void checkIndexForRemove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed! Require index >= 0 and index < size.");
        }
    }	
}
