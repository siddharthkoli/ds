import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String serverUrl = "rmi://" + args[0] + "/Server";
        ServerIntf serverImpl = (ServerIntf) Naming.lookup(serverUrl);

        double a = Double.valueOf(args[1]).doubleValue();
        double b = Double.valueOf(args[2]).doubleValue();

        System.out.println(serverImpl.add(a, b));
    }
}