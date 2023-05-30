import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ConcatModule.*;

public class Client {
    public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);
        org.omg.CORBA.Object nameServiceRef = orb.resolve_initial_references("NameService");
        NamingContextExt nceRef = NamingContextExtHelper.narrow(nameServiceRef);

        String name = "Concat";
        Concat idlRef = ConcatHelper.narrow(nceRef.resolve_str(name));

        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        System.out.println(idlRef.concat(s1, s2));
    }
}
