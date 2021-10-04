package http;

public class HttpConnection {



    public static void sendAddCommand(String name){
        System.out.println("send " + name);
    }

    public static void sendSearchCommand(String value, String parameterName){
        System.out.println("send parameter ?"+parameterName+"="+value);

    }
}
