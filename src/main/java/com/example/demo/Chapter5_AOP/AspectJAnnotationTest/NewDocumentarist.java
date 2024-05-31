package com.example.demo.Chapter5_AOP.AspectJAnnotationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("documentarist")
class NewDocumentarist {

    protected GrammyGuitarist guitarist;
    private Guitar guitar;

    public void execute() {
        guitarist.sing();
//        Guitar guitar = new Guitar();
//        guitar.setBrand("Gibson");
        guitarist.sing(this.guitar);
        guitarist.talk();
    }

//    @Autowired
//    @Qualifier("johnMayer")
    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

    public NewDocumentarist(GrammyGuitarist guitarist, Guitar guitar) {
        this.guitarist = guitarist;
        this.guitar = guitar;
    }
}
