interface Home{
    void fun();
}

class TestImpl implements Home{
    @Override
    void fun(){
        System.out.println("=============");
    }
}

class TestProxy implements Home{
    private TestImpl testImpl;
    public TestProxy(TestImpl testImpl){
        this.testImpl=testImpl;
    }
    void fun(){
        System.out.println("----------------");
        testImpl.fun();
    }
}


class Demo{
    public static void main(String[] args) {
        TestImpl testImpl = new TestImpl();
        TestProxy testProxy = new TestProxy(testImpl);
        testProxy.fun();
    }
}

/**
 *  缺点：如果想要为更多的类代理，则需要建立多个代理类，维护难度大
 * 原因：在编译器就确定了，如果是在编译器确定，这些问题就可以解决了
 */


