package kw.test.demo.builder;

import kw.test.demo.builder.builer.impl.MyBuidler;
import kw.test.demo.builder.director.impl.MyAirShipDirector;
import kw.test.demo.builder.ship.AirShip;

public class Client {
	public static void main(String[] args) {
		MyAirShipDirector director = new MyAirShipDirector(new MyBuidler());
		AirShip airShip = director.director();
	}
}
