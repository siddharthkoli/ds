import org.omg.*;
import ReverseModule.*;

// all Helper classes use narrow
// whenever CORBA.Object is created, Helper is used to get ref

public class ReverseServer {
    public static void main(String[] args) {
        try {
            // initialize orb
            ORB orb = ORB.init(args, null);

            // init poa using POAHelper
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			
			      // activate poa
            rootPOA.the_POAManager().activate();

            // creating intf impl obj
            ReverseImpl revImpl = new ReverseImpl();
			
            // set impl as servant to ref
            CORBA.Object implRef = rootPOA.servant_to_reference(revImpl);

            // get idl Ref via ReverseHelper using implRef
            Reverse idlRef = ReverseHelper.narrow(implRef);
			
            // resolve intial ref to NameService
            CORBA.Object nameServiceRef  = orb.resolve_initial_references("NameService");
            
            // get nceRef via nceHelper using nameServiceRef
            NamingContextExt nceRef = NamingContextExtHelper.narrow(nameServiceRef);
			
            // get nameComponent ref to intf using nceRef
            String name = "Reverse"; // this should be same as interface name in idl
            NameComponent path[] = nceRef.to_name(name);
			
            // rebind path to idlRef
            nceRef.rebind(path, idlRef);

            System.out.println("Server reading and waiting");
			      // run orb server
            orb.run();
        } catch (Exception e) {

        }
    }
}
