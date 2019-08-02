package kw.test.demo.factory.method;

public class BenChiMethodFactory implements MethodFactory {
	@Override
	public Car create() {
		// TODO Auto-generated method stub
		return new BenChi();
	}

}
