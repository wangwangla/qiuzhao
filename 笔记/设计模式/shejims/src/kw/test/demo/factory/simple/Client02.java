package kw.test.demo.factory.simple;

import kw.test.demo.factory.simple.car.Car;
import kw.test.demo.factory.simple.factory.ClientFactory;

/**
 * ʹ�ù�����ʱ�������õ����������빤���г������;Ϳ�����
 * @author Administrator
 *
 */
public class Client02 {
	public static void main(String[] args) {
		Car c = ClientFactory.createCar("Audi");
	}
}
