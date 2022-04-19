package text_editor.gui;

import text_editor.utils.BrokerUtils;

public class SingletonServer {

    private static SingletonServer single_instance = null ;

    private final String SERVER_QUEUE = "server_queue";

    public SingletonServer() {
        System.out.println("A unique instance is created");
        BrokerUtils.receive(SERVER_QUEUE);
    }

    public static SingletonServer getInstance(){
        if(single_instance == null ){
            single_instance = new SingletonServer() ;
        }
        return  single_instance ;
    }

    public String getServerQueue() {
        return SERVER_QUEUE;
    }
}
