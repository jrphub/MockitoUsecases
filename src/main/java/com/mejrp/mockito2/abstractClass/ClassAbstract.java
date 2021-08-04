package com.mejrp.mockito2.abstractClass;

public abstract class ClassAbstract {

    //abstract method
    public abstract int method1();

    //non-abstract method
    public int method2() {
        return 1;
    }

    //static method example
    public static int method3() {
        return 2;
    }
}
