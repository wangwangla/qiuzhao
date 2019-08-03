package kw.test.demo.builder.director.impl;

import kw.test.demo.builder.builer.impl.MyBuidler;
import kw.test.demo.builder.director.AirShipDirector;
import kw.test.demo.builder.ship.AirShip;

public class MyAirShipDirector implements AirShipDirector{
	//创建者调用构建者
	private MyBuidler myBuidler;
	
	public MyAirShipDirector(MyBuidler buidler) {
		// TODO Auto-generated constructor stub
		this.myBuidler = buidler;
	}
	@Override
	public AirShip director() {
		// TODO Auto-generated method stub
		String head = myBuidler.builderHead();
		String middle = myBuidler.buildermiddle();
		String tail = myBuidler.builderTail();
		
		AirShip airShip = new AirShip();
		airShip.setHead(head);
		airShip.setMiddle(middle);
		airShip.setTail("");
		return airShip;
	}

}
