package kw.test;

import io.jboot.codegen.model.JbootModelGenerator;
import io.jboot.codegen.service.JbootServiceGenerator;
import io.jboot.web.controller.JbootController;

/**
 * auther   kangwang
 */
public class GenDemo extends JbootController {
    public static void main(String[] args){
        String modelPackage = "kw.test.model";
        //生成service 的包名
        String basePackage = "kw.test.service";

        JbootModelGenerator.run(modelPackage);
        JbootServiceGenerator.run(basePackage, modelPackage);
    }
}
