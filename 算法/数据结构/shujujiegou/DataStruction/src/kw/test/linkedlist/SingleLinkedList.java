package kw.test.linkedlist;

public class SingleLinkedList {
	public static void main(String[] args) {
		HeroNode heroNode1 = new HeroNode(1, "XX", "sss");
		HeroNode heroNode2 = new HeroNode(1, "XX", "sss");
		HeroNode heroNode3 = new HeroNode(1, "XX", "sss");
		HeroNode heroNode4 = new HeroNode(1, "XX", "sss");
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.addLinkedList(heroNode1);
		singleLinkedList.addLinkedList(heroNode2);
		singleLinkedList.addLinkedList(heroNode3);
		singleLinkedList.addLinkedList(heroNode4);
		singleLinkedList.list();
	}
	private HeroNode head;
	public SingleLinkedList() {
		head = new HeroNode(0, "", "");
	}
	public void addLinkedList(HeroNode heroNode){
		HeroNode temp ;
		temp = head;
		while(temp.next!=null) {
			temp=temp.next;
		}
		temp.next = heroNode;
	}
	
	public void list() {
		if(head.next==null) {
			return;
		}
		HeroNode temp = head.next;
		while(temp!=null) {
			System.out.println(temp);
			temp = temp.next;
		}
		
		
	}
	
}
class HeroNode{
	public int no;
	public String name;
	public String nickName;
	public HeroNode next;
	public HeroNode(int no,String name,String nickName) {
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}
	
}