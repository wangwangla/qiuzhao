package kw.test.queue;

/**
 *     ����
 *     �洢on
 *     ȡ��out
 *     ͵��peak
 * @author Administrator
 *
 */
public class ArrayQueue {
	//ʹ������ʵ��
	private int data[];
	private int maxSize;
	private int rear;//β��   β��������
	private int font;//ͷ   ͷ��������
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
			throw new RuntimeException("��������!");
		}
		rear++;
		data[rear] = value;
	}
	
	public int getQueue(){
		if(isEmpty()) {
			throw new RuntimeException("����Ϊ�գ�");
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
