package kw.test.demo.builder.builer.impl;

import kw.test.demo.builder.builer.AirShipBuilder;

public class MyBuidler implements AirShipBuilder{

	@Override
	public String builderHead() {
		// TODO Auto-generated method stub
		return "head";
	}

	@Override
	public String buildermiddle() {
		// TODO Auto-generated method stub
		return "middle";
	}

	@Override
	public String builderTail() {
		// TODO Auto-generated method stub
		return "tail";
	}

}
