package kw.test.queue;

public class CircleArrayQueueDemo {
	//使用数组实现
	private int data[];
	private int maxSize;
	private int rear;//尾部   尾部加数据,尾部是最后一个元素的下一个元素
	private int font;//头   头部出数据
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
			//throw new RuntimeException("队列已满!");
			System.out.println("队列已满");
			return ;
		}
		data[rear] = value;
		rear=(rear+1)%maxSize;
	}
		
	public int getQueue(){
		if(isEmpty()) {
			throw new RuntimeException("队列为空！");
		}
		int value = data[font];
		font=(font+1)%maxSize;
		return value;
	}
	
	public void showQueue(){
		if(isEmpty()) {
			throw new RuntimeException("队列为空！");
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
