package kw.test.demo.factory.simple.factory;

import kw.test.demo.factory.simple.car.Car;
import kw.test.demo.factory.simple.car.impl.Audi;
import kw.test.demo.factory.simple.car.impl.BenChi;

/**
 * ���ַ�ʽΥ���˿���ԭ��
 * 
 * ������ʽ1
 * @author Administrator
 *
 */
public class ClientFactory {
	public static Car createCar(String type) {
		if("Audi".equals(type)) {
			return new Audi();
		}else if("BenChi".equals(type)){
			return new BenChi();	
		}else {
			return null;
		}
	}
}
