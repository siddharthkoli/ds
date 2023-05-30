import java.rmi.*;

public abstract class Server {
    public static void main(String[] args) throws Exception {
        ServerImpl serverImpl = new ServerImpl();
        Naming.bind("Server", serverImpl);
    }
}