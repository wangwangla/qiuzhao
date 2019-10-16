package kw.test.stack;

public class ArrayStack {
	private int stack[];
	private int maxSize;
	private int top;
	public ArrayStack(int size) {
		this.maxSize = size;
		this.top = -1;
		this.stack = new int[maxSize];
	}
	//判断满
	public boolean isFull() {
		return top == maxSize - 1;
	}
	//判断空
	public boolean isEmpty() {
		return top == -1;
	}
	//加入
	public void push(int value) {
		if(isFull()) {
			System.out.println("已经满了!");
			return;
		}
		top++;
		stack[top] = value;
	}
	//返回
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("为空");
		}
		int value = stack[top];
		top--;
		return value;
	}
	public void show() {
		if(isEmpty()) {
			System.out.println("为空！");
		}
		for(int i=top;i>=0;i--) {
			System.out.println(stack[i]+" ");
		}
	}
	public static void main(String[] args) {
		ArrayStack arrayStack = new ArrayStack(4);
		arrayStack.push(3);
		arrayStack.push(4);
		arrayStack.push(1);
		arrayStack.push(6);
		arrayStack.push(9);
		arrayStack.show();
		
	}
}
