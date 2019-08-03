package kw.test.demo.factory.abstractf.part.engine.impl;

import kw.test.demo.factory.abstractf.part.engine.Engine;

public class DiduanEngine implements Engine{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run  slow");
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("diduan engine is start");
	}
}
