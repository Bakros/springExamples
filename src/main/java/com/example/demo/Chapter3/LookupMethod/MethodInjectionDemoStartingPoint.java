package com.example.demo.Chapter3.LookupMethod;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

import static java.lang.System.out;

public class MethodInjectionDemoStartingPoint {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(LookupConfig.class);

        var abstractLockOpener = ctx.getBean("abstractLockOpener", LockOpener.class);
        displayInfo("abstractLockOpener", abstractLockOpener);

       // var standardLockOpener = ctx.getBean("standardLockOpener", LockOpener.class);
       // displayInfo("standardLockOpener", standardLockOpener);
    }
    public static void displayInfo(String beanName, LockOpener lockOpener) {
        var keyHelperOne = lockOpener.getMyKeyOpener();
        var keyHelperTwo = lockOpener.getMyKeyOpener();

        out.println("[" + beanName + "]: KeyHelper Instances the Same?  " + (keyHelperOne == keyHelperTwo));

        var stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");

        for (var x = 0; x < 100_000; x++) {
            var keyHelper = lockOpener.getMyKeyOpener();
            keyHelper.open();
        }
        stopWatch.stop();
        out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }

}
