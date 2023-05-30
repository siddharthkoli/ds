import CalculatorModule.Calculator;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


import CalculatorModule.CalculatorHelper;

public class Server {
    public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);
        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootPOA.the_POAManager().activate();

        CalculatorImpl calcImpl = new CalculatorImpl();
        org.omg.CORBA.Object implRef = rootPOA.servant_to_reference(calcImpl);
        Calculator idlRef = CalculatorHelper.narrow(implRef);

        org.omg.CORBA.Object nameServiceRef = orb.resolve_initial_references("NameService");
        NamingContextExt nceRef = NamingContextExtHelper.narrow(nameServiceRef);

        String name = "Calculator";
        NameComponent path[] = nceRef.to_name(name);
        nceRef.rebind(path, idlRef);

        System.out.println("Server started and waiting");
        orb.run();
    }
}