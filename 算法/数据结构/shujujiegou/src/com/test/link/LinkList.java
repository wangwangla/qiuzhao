package com.test.link;

public class LinkList {
	private Link first;
	public LinkList() {
		first = null;
	}
	public boolean isEmpty(){
		return first==null;
	}
	
	public void insertFirst(int id,int dd){
		Link newLink = new Link(id,dd);
		newLink.next = first;
		first = newLink;
	}
	
	public Link deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp;
	}
	
	private Link find(int key) {
		// TODO Auto-generated method stub
		Link l = first;
		while(l.iData!=key) {
			if(l.next==null) {
				return null;
			}else {
				l= l.next;
			}
		}
		return l;
	}
	
	public void disPlayList() {
		System.out.println("List--->first");
		Link current = first;
		while(current!=null) {
			current.disPlayLink();
			current = current.next;
		}
		System.out.println("");
	}
}
