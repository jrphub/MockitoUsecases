package com.mejrp.mockito2.voidMethods;

public class PublishService {
    private final Publisher publisher;

    public PublishService(Publisher publisher) {
        this.publisher = publisher;
    }

    public void invokePublisher(News news) {
        //void method to be mocked
        publisher.publishInfo(news);
    }
}
