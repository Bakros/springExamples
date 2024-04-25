package com.example.demo.Chapter3.Prototype_SIngleton;

import com.example.demo.Chapter3.LookupMethod.LookupConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.out;

public class Prototype_Singleton {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(BeanConfig.class);

        String[] allBeanNames = ctx.getBeanDefinitionNames();
        System.out.println("\n==========Beans managed by ctx configured with @Configurator==========");
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
        System.out.println("\n==========Beans managed by ctx configured with @Configurator==========\n");


        var clockManEurope = ctx.getBean("clockManEurope", ClockMan.class);
        out.println(clockManEurope.getClock());
        var clockManAmerica = ctx.getBean("clockManAmerica", ClockMan.class);
        out.println(clockManAmerica.getClock());
    }


}


class ClockMan{
    public Clock clock;
    public String prueba = "Test";

    @Autowired
    public void setClock(Clock clock) {
        out.println("Clock set=============================");
        this.prueba = "I'm here too!";
        this.clock = clock;
    }

    public Clock getClock() {
        return clock;
    }
}


@Configuration
@ComponentScan
class BeanConfig{
    @Bean(name="clockManEurope")
    public ClockMan theFirst(){
        return new ClockMan();
    }

    @Bean(name="clockManAmerica")
    public ClockMan theSecond(){
        return new ClockMan();
    }

}

@Component
@Scope("prototype")
class Clock{
    public String tellTime(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
}
