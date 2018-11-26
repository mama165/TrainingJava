package DesignPattern.singleton;

public class SingletonDemo {
    public static void main(String[] args) {

        //illegal construct
        //Compile Time Error: The constructor SingleObject() is not visible
        //Singleton object = new Singleton();

        //Get the only object available
        Singleton singleton = Singleton.getInstance();

        //show the message
        singleton.showMessage();
    }
}