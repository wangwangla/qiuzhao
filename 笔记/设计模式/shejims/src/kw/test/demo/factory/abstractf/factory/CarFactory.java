package kw.test.demo.factory.abstractf.factory;

import kw.test.demo.factory.abstractf.part.engine.Engine;
import kw.test.demo.factory.abstractf.part.seat.Seat;
import kw.test.demo.factory.abstractf.part.tyer.Tyer;

public interface CarFactory {
	Engine createEngine();
    Seat createSeat();
	Tyer createTyer();
}
