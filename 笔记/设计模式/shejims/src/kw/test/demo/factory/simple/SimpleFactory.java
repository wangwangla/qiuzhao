package kw.test.demo.factory.simple;

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
