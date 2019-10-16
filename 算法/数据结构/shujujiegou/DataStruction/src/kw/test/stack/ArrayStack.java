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
	//�ж���
	public boolean isFull() {
		return top == maxSize - 1;
	}
	//�жϿ�
	public boolean isEmpty() {
		return top == -1;
	}
	//����
	public void push(int value) {
		if(isFull()) {
			System.out.println("�Ѿ�����!");
			return;
		}
		top++;
		stack[top] = value;
	}
	//����
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("Ϊ��");
		}
		int value = stack[top];
		top--;
		return value;
	}
	public void show() {
		if(isEmpty()) {
			System.out.println("Ϊ�գ�");
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
