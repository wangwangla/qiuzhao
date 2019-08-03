package kw.test.demo.factory.simple.factory;

import kw.test.demo.factory.simple.car.Car;
import kw.test.demo.factory.simple.car.impl.Audi;
import kw.test.demo.factory.simple.car.impl.BenChi;

/**
 * �򷱹�����ʽ2
 * @author Administrator
 *
 */
public class SimpleFactory {
	public static Car createAudi() {
		return new Audi();
	}
	public static Car createBenChi() {
		return new BenChi();
	}
	
}
