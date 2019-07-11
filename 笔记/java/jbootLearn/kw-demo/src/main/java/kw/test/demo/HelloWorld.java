package kw.test.demo;

import io.jboot.Jboot;
import io.jboot.codegen.model.JbootModelGenerator;
import io.jboot.codegen.service.JbootServiceGenerator;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/")
public class HelloWorld extends JbootController{
   public void index(){
        renderText("hello jboot");
   }
   
   public static void main(String [] args){
       //依赖model的包名
  /*     String modelPackage = "kw.test.model";
       //生成service 的包名
       String basePackage = "kw.test.service";

       JbootModelGenerator.run(modelPackage);
       JbootServiceGenerator.run(basePackage, modelPackage);
	   */
       Jboot.run(args);
   }
}