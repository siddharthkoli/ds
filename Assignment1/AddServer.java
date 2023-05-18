import java.rmi.*;

public abstract class AddServer {
    public static void main(String[] args) {
        try{
            AddServerImpl addServerImpl = new AddServerImpl();
            Naming.bind("AddServer", addServerImpl); 
            Naming.bind("SubServer", addServerImpl);    
        } catch(Exception e) {
            System.out.println("Exception " + e);
        }        
    }    
}
