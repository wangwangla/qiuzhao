package kw.test.demo.factory.abstractf;

import kw.test.demo.factory.abstractf.factory.impl.GaojiFactory;
import kw.test.demo.factory.abstractf.part.seat.impl.GaojiSeat;

public class Client {
	public static void main(String[] args) {
		GaojiFactory gaojiFactory = new GaojiFactory();
		gaojiFactory.createEngine();
		//бнбн
	}
}
