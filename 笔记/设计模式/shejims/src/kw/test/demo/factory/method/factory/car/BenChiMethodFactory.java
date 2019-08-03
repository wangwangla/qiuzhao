package kw.test.demo.factory.method.factory.car;

import kw.test.demo.factory.method.car.Car;
import kw.test.demo.factory.method.car.impl.BenChi;
import kw.test.demo.factory.method.factory.MethodFactory;

public class BenChiMethodFactory implements MethodFactory {
	@Override
	public Car create() {
		// TODO Auto-generated method stub
		return new BenChi();
	}

}
