package kw.test.demo.factory.simple;

import kw.test.demo.factory.simple.car.Car;
import kw.test.demo.factory.simple.factory.ClientFactory;

/**
 * 使用工厂的时候，我们拿到工厂，传入工厂中车的类型就可以了
 * @author Administrator
 *
 */
public class Client02 {
	public static void main(String[] args) {
		Car c = ClientFactory.createCar("Audi");
	}
}
