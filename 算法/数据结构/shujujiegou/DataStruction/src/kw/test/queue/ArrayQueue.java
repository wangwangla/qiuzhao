package kw.test.queue;

/**
 *     队列
 *     存储on
 *     取出out
 *     偷看peak
 * @author Administrator
 *
 */
public class ArrayQueue {
	//使用数组实现
	private int data[];
	private int maxSize;
	private int rear;//尾部   尾部加数据
	private int font;//头   头部出数据
	public ArrayQueue(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		this.data = new int[maxSize];
		font = -1;
		rear = -1;
	}
	
	public boolean isFull(){
		return rear == maxSize -1;
	}
	
	public boolean isEmpty(){
		return rear == font;
	}
	
	public void addQueue(int value){
		if(isFull()) {
			throw new RuntimeException("队列已满!");
		}
		rear++;
		data[rear] = value;
	}
	
	public int getQueue(){
		if(isEmpty()) {
			throw new RuntimeException("队列为空！");
		}
		font++;
		return data[font];
	}
	
	public void showQueue(){
		for(int i=font+1;i<=rear;i++) {
			System.out.println(data[i]);
		}
	}
	
	public int headQueue() {
		return data[font+1];
	}
	
	public static void main(String[] args) {
		ArrayQueue a = new ArrayQueue(5);
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
		System.out.println(a.headQueue());
	}
}
