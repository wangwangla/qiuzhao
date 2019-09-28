package kw.test.queue;

public class CircleArrayQueueDemo {
	//ʹ������ʵ��
	private int data[];
	private int maxSize;
	private int rear;//β��   β��������,β�������һ��Ԫ�ص���һ��Ԫ��
	private int font;//ͷ   ͷ��������
	public CircleArrayQueueDemo(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		this.data = new int[maxSize];
	}
	
	public boolean isFull() {
		return (rear+1)% maxSize==font;
	}
	
	public boolean isEmpty() {
		return rear==font;
	}
	
	public void addQueue(int value){
		if(isFull()) {
			//throw new RuntimeException("��������!");
			System.out.println("��������");
			return ;
		}
		data[rear] = value;
		rear=(rear+1)%maxSize;
	}
		
	public int getQueue(){
		if(isEmpty()) {
			throw new RuntimeException("����Ϊ�գ�");
		}
		int value = data[font];
		font=(font+1)%maxSize;
		return value;
	}
	
	public void showQueue(){
		if(isEmpty()) {
			throw new RuntimeException("����Ϊ�գ�");
		}
		for(int i=font;i<font+size();i++) {
			System.out.println(data[i%maxSize]);
		}
	}
	
	public int size() {
		return (rear+maxSize-font)%maxSize;
	}
	public static void main(String[] args) {
		CircleArrayQueueDemo a = new CircleArrayQueueDemo(5);
		a.addQueue(1);
		a.addQueue(2);
		a.addQueue(3);
		a.addQueue(4);
		a.addQueue(4);
		a.showQueue();
		System.out.println("-----------");
		System.out.println(a.getQueue());
	    System.out.println("-----------");
		a.showQueue();
		System.out.println("-----------");
	}
	
}
