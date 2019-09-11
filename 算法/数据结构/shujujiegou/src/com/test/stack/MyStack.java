package com.test.stack;

public class MyStack {
	private int maxSize;
	private char [] stackArray;
	private int top;
	public MyStack(int s) {
		// TODO Auto-generated constructor stub
		stackArray = new char[s];
		maxSize = s;
		top = -1;
	}
	public void push(char v) {
		stackArray[++top]=v;
	}
	public char pop() {
		return stackArray[top--];
	}
	public boolean isEmpty() {
		return top==-1;
	}
	public boolean isFull() {
		return top==maxSize-1;
	}
	public void print() {
		while(!isEmpty()) {
			System.out.print(pop());
		/*for(int i = 0;i<=top;i++) {
			System.out.print(stackArray[i]);
		}
		System.out.println();*/
		}
	}
}

