package kw.test.demo.factory.abstractf.factory.impl;

import kw.test.demo.factory.abstractf.factory.CarFactory;
import kw.test.demo.factory.abstractf.part.engine.Engine;
import kw.test.demo.factory.abstractf.part.engine.impl.GaojiEngine;
import kw.test.demo.factory.abstractf.part.seat.Seat;
import kw.test.demo.factory.abstractf.part.seat.impl.GaojiSeat;
import kw.test.demo.factory.abstractf.part.tyer.Tyer;
import kw.test.demo.factory.abstractf.part.tyer.impl.GaojiTyer;

public class GaojiFactory implements CarFactory {

	@Override
	public Engine createEngine() {
		// TODO Auto-generated method stub
		return new GaojiEngine();
	}

	@Override
	public Seat createSeat() {
		// TODO Auto-generated method stub
		return new GaojiSeat();
	
	}

	@Override
	public Tyer createTyer() {
		// TODO Auto-generated method stub
		return new GaojiTyer();
	}
}
