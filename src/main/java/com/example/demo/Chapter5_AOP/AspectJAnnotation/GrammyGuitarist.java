package com.example.demo.Chapter5_AOP.AspectJAnnotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("documentarist")
class NewDocumentarist {
    protected GrammyGuitarist guitarist;
    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();
    }
    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}

@Component("johnMayer")
public class GrammyGuitarist implements Singer {
    private static Logger LOGGER = LoggerFactory.getLogger(GrammyGuitarist.class);
    @Override
    public void sing() {
        LOGGER.info("sing: Gravity is working against me\n" +
                "And gravity wants to bring me down");
    }
    public void sing(Guitar guitar) {
        LOGGER.info("play: " + guitar.play());
    }
    public void talk(){
        LOGGER.info("talk");
    }
    @Override
    public void rest(){
        LOGGER.info("zzz");
    }
}

class Guitar {
    private String brand =" Martin";

    public String play(){
        return "G C G C Am D7";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

interface Singer {
    Logger LOGGER = LoggerFactory.getLogger(Singer.class);

    void sing();

    default void sing(String key){
        LOGGER.info("Singing in the key of {}", key);
    }

    default void rest(){
    }
}