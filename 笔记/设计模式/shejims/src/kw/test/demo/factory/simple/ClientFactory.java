package kw.test.demo.factory.simple;

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
			return new Audi();	
		}else {
			return null;
		}
	}
}
