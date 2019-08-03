package kw.test.demo.factory.abstractf.factory.impl;

import kw.test.demo.factory.abstractf.factory.CarFactory;
import kw.test.demo.factory.abstractf.part.engine.Engine;
import kw.test.demo.factory.abstractf.part.engine.impl.DiduanEngine;
import kw.test.demo.factory.abstractf.part.seat.Seat;
import kw.test.demo.factory.abstractf.part.seat.impl.DiduanSeat;
import kw.test.demo.factory.abstractf.part.tyer.Tyer;
import kw.test.demo.factory.abstractf.part.tyer.impl.DiduanTyer;

public class DiduanFactory implements CarFactory{

	@Override
	public Engine createEngine() {
		// TODO Auto-generated method stub
		return new DiduanEngine();
	}

	@Override
	public Seat createSeat() {
		// TODO Auto-generated method stub
		return new DiduanSeat();
	}

	@Override
	public Tyer createTyer() {
		// TODO Auto-generated method stub
		return new DiduanTyer();
	}

}
