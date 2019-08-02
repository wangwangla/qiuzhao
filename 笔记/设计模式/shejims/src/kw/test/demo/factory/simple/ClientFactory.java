package kw.test.demo.factory.simple;

/**
 * 这种方式违背了开闭原则
 * 
 * 工厂方式1
 * @author Administrator
 *
 */
public class ClientFactory {
	public static Car createCar(String type) {
		if("Audi".equals(type)) {
			return new Audi();
		}else if("BenChi".equals(type)){
			return new Audi();	
		}else {
			return null;
		}
	}
}
