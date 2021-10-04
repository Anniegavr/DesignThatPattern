package Client;


public class Client {

    private static Client client;

    private Client(){}

    public static synchronized Client getClient(){
        if(client == null){
            client = new Client();
        }
        return client;
    }

    public static Client getInstanceUsingDoubleLocking(){
        if(client == null){
            synchronized (Client.class) {
                if(client == null){
                    client = new Client();
                }
            }
        }
        return client;
    }
}
