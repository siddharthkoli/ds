import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import ConcatModule.Concat;
import ConcatModule.ConcatHelper;

public class Server {
    public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);
        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootPOA.the_POAManager().activate();

        ConcatImpl conImpl = new ConcatImpl();
        org.omg.CORBA.Object implRef = rootPOA.servant_to_reference(conImpl);
        Concat idlRef = ConcatHelper.narrow(implRef);

        org.omg.CORBA.Object nameServiceRef = orb.resolve_initial_references("NameService");
        NamingContextExt nceRef = NamingContextExtHelper.narrow(nameServiceRef);

        String name = "Concat";
        NameComponent path[] = nceRef.to_name(name);
        nceRef.rebind(path, idlRef);
        System.out.println("server initiated and waiting");

        orb.run();
    }
}
