package kw.test.demo.factory.simple;

/**
 * 不是工厂的时候，我们创建汽车的时候的创建方式
 * @author Administrator
 *
 */
public class Client01 {
	public static void main(String[] args) {
		Car c1 = new Audi();
		BenChi benChi = new BenChi();
	}
}
