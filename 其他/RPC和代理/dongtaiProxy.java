import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.sun.java.util.jar.pack.Package.Class.Method;

interface Home{
    void fun();
}

class TestImpl implements Home{
    @Override
    void fun(){
        System.out.println("=============");
    }
}

class TestProxy implements InvocationHandler{
    Object object ;
    public Object bind(Object object){
        this.object = object;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), Home, this);
    }
    public Object invoke(Object proxy,java.lang.reflect.Method method,Object[]args){
        System.out.println("===============");
        Object res = method.invoke(obj, args);
        return res; 
    }
}





