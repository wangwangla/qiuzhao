package com.test.array;

/**
 *	 ����
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
	 // �� index λ�ã�����Ԫ��e, ʱ�临�Ӷ� O(m+n)
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

    // ������ͷ����Ԫ��
    public void addFirst(T e) {
    	if(data.length-1==size) {
    		throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
    	}
    	for(int i=size;i>0;i--) {
    		data[i]=data[i-1];
    	}
    	size++;
    }

    // ������β����Ԫ��
    public void addLast(T e) {
    	if(data.length-1==size) {
    		throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
    	}
    	data[++size]=e;
    }

    // ɾ�� index λ�õ�Ԫ�أ�������
    public T remove(int index) {
    	T t = data[index];
    	for(int i=index ; i<size;i--) {
    		data[i]=data[i+1];
    	}
    	size--;
		return t;
    }

    // ɾ����һ��Ԫ��
    public T removeFirst() {
    	T t = data[0];
    	for(int i=0;i<size-1;i++) {
    		data[i]=data[i+1];
    	}
    	size--;
		return t;
    }

    // ɾ��ĩβԪ��
    public T removeLast() {
    	T t = data[size-1];
    	size--;
		return t;
    }

    // ��������ɾ��ָ��Ԫ��
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


    // ���ݷ�����ʱ�临�Ӷ� O(n)
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
