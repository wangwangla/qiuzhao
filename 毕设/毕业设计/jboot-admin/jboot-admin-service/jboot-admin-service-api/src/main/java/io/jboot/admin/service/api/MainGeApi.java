package io.jboot.admin.service.api;

import io.jboot.codegen.service.JbootServiceGenerator;

public class MainGeApi {
	public static void main(String[] args) {
			//生成service 的包名
    	String basePackage = "com.bysj.service.api";
    	//依赖model的包名
    	String modelPackage = "com.bysj.model.entity";
    	JbootServiceGenerator.run(basePackage, modelPackage);
	}
}
