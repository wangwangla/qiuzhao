package kw.test.demo.factory.method;

import kw.test.demo.factory.method.car.Car;
import kw.test.demo.factory.method.factory.car.AudiMethodFactory;
import kw.test.demo.factory.method.factory.car.BenChiMethodFactory;

public class Client {
	public static void main(String[] args) {
		Car c1 = new AudiMethodFactory().create();
		Car c2 = new BenChiMethodFactory().create();
		
		c1.run();
		c2.run();
	}
}
