package designPattern.template;

public abstract class Game {
    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    //template extractLines
    public final void play() {

        //initialize the game
        initialize();

        //start game
        startPlay();

        //end game
        endPlay();
    }
}