package com.example.demo.Chapter4.Event;

import ch.qos.logback.core.spi.ContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Publisher implements ApplicationContextAware {

    ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

/*
    public void publish(String message){
        ctx.publishEvent(new MessageEvent(this, message));
    }

    public void publish2(String message){
        ctx.publishEvent(new MessageEvent2(this, message));
    }*/
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(EventsConfig.class);

        ctx.publishEvent(new MessageEvent(ctx, "I send an SOS to the world..."));
        ctx.publishEvent(new MessageEvent(ctx, ".. I hope that someone gets my..."));
        ctx.publishEvent(new MessageEvent(ctx, "... Message in a bottle"));


        /*
        Publisher pub = (Publisher) ctx.getBean("publisher");
        pub.publish("I send an SOS to the world... ");
        pub.publish("... I hope that someone gets my...");
        pub.publish("... Message in a bottle");*/

       // pub.publish2("HEllo");

    }

}



@Configuration
@ComponentScan
class EventsConfig{ }


class MessageEvent extends ApplicationEvent{
    private String msg;
    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
    public String getMessage(){
        return msg;
    }
}

@Component
class MessageEventListener implements ApplicationListener<MessageEvent>{
    private static Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void onApplicationEvent(MessageEvent event) {
        logger.info("Received: {}" , event.getMessage());
    }
}


@Component
class MessageEventListener2 implements ApplicationListener<MessageEvent>{
    private static Logger logger = LoggerFactory.getLogger(MessageEventListener2.class);

    @Override
    public void onApplicationEvent(MessageEvent event) {
        logger.info("Got it: {}" , event.getMessage());
    }
}

@Component
class MessageEventListener3 implements ApplicationListener<MessageEvent2>{
    private static Logger logger = LoggerFactory.getLogger(MessageEventListener3.class);

    @Override
    public void onApplicationEvent(MessageEvent2 event) {
        logger.info("Got it - MessageEvent2: {}" , event.getMessage());
    }
}

class MessageEvent2 extends ApplicationEvent{
    private String msg;
    public MessageEvent2(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
    public String getMessage(){
        return msg;
    }
}