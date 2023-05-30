import java.rmi.*;

interface ServerIntf extends Remote {
    double add(double a, double b) throws RemoteException;
}