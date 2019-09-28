package kw.test.queue;

public class Demo {
	private int maxSize; // ��ʾ������������
	//front �����ĺ�����һ�������� front ��ָ����еĵ�һ��Ԫ��, Ҳ����˵ arr[front] ���Ƕ��еĵ�һ��Ԫ�� 
	//front �ĳ�ʼֵ = 0
	private int front; 
	//rear �����ĺ�����һ��������rear ָ����е����һ��Ԫ�صĺ�һ��λ��. ��Ϊϣ���ճ�һ���ռ���ΪԼ��.
	//rear �ĳ�ʼֵ = 0
	private int rear; // ����β
	private int[] arr; // ���������ڴ������, ģ�����
	
	public Demo(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	
	// �ж϶����Ƿ���
	public boolean isFull() {
		return (rear  + 1) % maxSize == front;
	}
	
	// �ж϶����Ƿ�Ϊ��
	public boolean isEmpty() {
		return rear == front;
	}
	
	// ������ݵ�����
	public void addQueue(int n) {
		// �ж϶����Ƿ���
		if (isFull()) {
			System.out.println("�����������ܼ�������~");
			return;
		}
		//ֱ�ӽ����ݼ���
		arr[rear] = n;
		//�� rear ����, ������뿼��ȡģ
		rear = (rear + 1) % maxSize;
	}
	
	// ��ȡ���е�����, ������
	public int getQueue() {
		// �ж϶����Ƿ��
		if (isEmpty()) {
			// ͨ���׳��쳣
			throw new RuntimeException("���пգ�����ȡ����");
		}
		// ������Ҫ������ front��ָ����еĵ�һ��Ԫ��
		// 1. �Ȱ� front ��Ӧ��ֵ������һ����ʱ����
		// 2. �� front ����, ����ȡģ
		// 3. ����ʱ����ı�������
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;

	}
	
	// ��ʾ���е���������
	public void showQueue() {
		// ����
		if (isEmpty()) {
			System.out.println("���пյģ�û������~~");
			return;
		}
		// ˼·����front��ʼ�������������ٸ�Ԫ��
		// ���Խ�
		for (int i = front; i < front + size() ; i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}
	
	// �����ǰ������Ч���ݵĸ���
	public int size() {
		// rear = 2
		// front = 1
		// maxSize = 3 
		return (rear + maxSize - front) % maxSize;   
	}
	
	// ��ʾ���е�ͷ���ݣ� ע�ⲻ��ȡ������
	public int headQueue() {
		// �ж�
		if (isEmpty()) {
			throw new RuntimeException("���пյģ�û������~~");
		}
		return arr[front];
	}
	public static void main(String[] args) {
		Demo a = new Demo(5);
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
