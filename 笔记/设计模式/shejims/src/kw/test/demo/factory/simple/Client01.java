package kw.test.demo.factory.simple;

import kw.test.demo.factory.simple.car.Car;
import kw.test.demo.factory.simple.car.impl.Audi;
import kw.test.demo.factory.simple.car.impl.BenChi;

/**
 * ���ǹ�����ʱ�����Ǵ���������ʱ��Ĵ�����ʽ
 * @author Administrator
 *
 */
public class Client01 {
	public static void main(String[] args) {
		Car c1 = new Audi();
		BenChi benChi = new BenChi();
	}
}
