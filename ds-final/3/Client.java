import java.util.Scanner;

import CalculatorModule.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
    public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);
        org.omg.CORBA.Object nameServiceRef = orb.resolve_initial_references("NameService");
        NamingContextExt nceRef = NamingContextExtHelper.narrow(nameServiceRef);

        String name = "Calculator";
        Calculator calcimpl = CalculatorHelper.narrow(nceRef.resolve_str(name));

        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        System.out.println(calcimpl.add(a, b));
    }
}