package com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2;

//@Component("documentarist")
class NewDocumentarist {

    protected GrammyGuitarist guitarist;
    private Guitar guitar;

    public void execute() {
        guitarist.sing();
        guitarist.sing(this.guitar);
        guitarist.talk();
    }

    public GrammyGuitarist getGuitarist() {
        return guitarist;
    }

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

    public NewDocumentarist(GrammyGuitarist guitarist, Guitar guitar) {
        this.guitarist = guitarist;
        this.guitar = guitar;
    }
}
