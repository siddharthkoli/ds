import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {
    public ServerImpl() throws RemoteException {}

    @Override
    public double add(double a, double b) throws RemoteException {
        return a + b;
    }
}