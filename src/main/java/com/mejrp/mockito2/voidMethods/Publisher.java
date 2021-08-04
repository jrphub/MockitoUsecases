package com.mejrp.mockito2.voidMethods;

public class Publisher {
    public void publishInfo(News news) {
        System.out.println("News :" + news.getMsg());
    }
}
