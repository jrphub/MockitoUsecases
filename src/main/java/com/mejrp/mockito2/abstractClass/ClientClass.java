package com.mejrp.mockito2.abstractClass;

public class ClientClass {

    private ClassAbstract classA;

    //getter & setter
    public ClassAbstract getClassA() {
        return classA;
    }

    public void setClassA(ClassAbstract classA) {
        this.classA = classA;
    }

    public int callMethod1A() {
        return classA.method1();
    }

    public int callMethod2A() {
        return classA.method2();
    }

    public int callMethod3() {
        return ClassAbstract.method3();
    }
}
