package com.test.link;

public class Link {
	public int iData;
	public double dData;
	public Link next;
	public Link(int id,double d) {
		// TODO Auto-generated constructor stub
		this.iData = id;
		this.dData = d;
	}
	public void disPlayLink() {
		System.out.println(iData+"  "+dData);
	}
}
