package text_editor.gui;

import java.util.ArrayList;

public class SingletonSender {

    private static SingletonSender single_instance = null ;

    private static ArrayList<App> my_app = new ArrayList<>() ;

    public SingletonSender() {
        System.out.println("A unique instance is created");
    }

    public static SingletonSender getInstance(){
        if(single_instance == null ){
            single_instance = new SingletonSender() ;
        }else {
            System.out.println("you already have an instnce");
        }
        return  single_instance ;
    }


    public App getAppByID(int id){
        if (id >App.getN() || id<0 ){
            System.err.println("invalid id , there is an error");
            System.exit(1);
        }
        else {
            return my_app.get(id);
        }
        return null ;
    }

    public ArrayList<App> getMy_app() {
        return my_app;
    }

    public static void setMy_app(ArrayList<App> my_app) {
        SingletonSender.my_app = my_app;
    }

    public static void main(String[] args) {

    }
}
