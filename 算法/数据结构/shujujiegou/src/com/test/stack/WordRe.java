package com.test.stack;

public class WordRe {
	public static void main(String[] args) {
		String str = "abc";
		char[] c= str.toCharArray();
		MyStack myStack = new MyStack(4);
		for(int i=0;i<c.length;i++) {
			myStack.push(c[i]);
		}
		myStack.print();
	}
}
