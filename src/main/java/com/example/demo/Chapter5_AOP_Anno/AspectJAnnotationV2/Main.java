package com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/***
 *  A newDocumentarist includes as a member a GrammyGuitarrist object.
 *  A GrammyGuitarrist implements the Singer interface. It also has a method sing that receives a Guitar a parameter.
 *
 *  newDocumentaris -> GrammyGuitarrist -> Guitar
 */


public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class, BeforeAdvice.class);
        ctx.refresh();

        /*
         NewDocumentarist newDocumentarist = new NewDocumentarist(
                (GrammyGuitarist) ctx.getBean("eltonMayer", GrammyGuitarist.class),
                new Guitar("Gibson"));
         */
        NewDocumentarist newDocumentarist = new NewDocumentarist(
                (GrammyGuitarist) ctx.getBean("johnMayer", GrammyGuitarist.class),
                new Guitar("Gibson"));

        newDocumentarist.execute();

        System.out.println("");System.out.println("");
        LoggerFactory.getLogger(Main.class).info("changing the assignation for the NewDocumentarist object!");

        newDocumentarist = new NewDocumentarist(
                (GrammyGuitarist) ctx.getBean("johnMinor", GrammyGuitarist.class),
                new Guitar("Fender"));

        newDocumentarist.execute();

        System.out.println("");System.out.println("");
        LoggerFactory.getLogger(Main.class).info("Example new object not managed by Spring ===================================================");

        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ctx;
        GenericApplicationContext genericApplicationContext = (GenericApplicationContext) configurableApplicationContext;

        //Create the GrammyGuitarist and add it to the ApplicationContext
        GrammyGuitarist myGrammyGuitarist = new GrammyGuitarist();
        genericApplicationContext.registerBean("johnMyGrammyGuitaristBean", GrammyGuitarist.class, () -> myGrammyGuitarist);

        var myGrammyGuitaristBean = (GrammyGuitarist) ctx.getBean("johnMyGrammyGuitaristBean");

        //Create the NewDocumentarist with the bean myGrammyGuitaristBean
        NewDocumentarist myDocumentarist = new NewDocumentarist(
                myGrammyGuitaristBean,
                new Guitar("Gibson"));
        myDocumentarist.execute();

        LoggerFactory.getLogger(Main.class).info("The object NOT managed by Spring myDocumentarist has the following object as a GrammyGuitarist member > {} ", myDocumentarist.getGuitarist().getClass().getName());

        LoggerFactory.getLogger(Main.class).info("End Example.");

        System.out.println("");System.out.println("");System.out.println("");
        LoggerFactory.getLogger(Main.class).info("=================================================== All the Bean managed by Spring!");
        displayAllBeans(ctx);
        ctx.close();

    }

    public static void displayAllBeans(ApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("Beans in ApplicationContext:");
        for (String beanName : beanNames) {
            System.out.println(beanName + " : " + context.getBean(beanName).getClass().getName());
        }
    }
}
