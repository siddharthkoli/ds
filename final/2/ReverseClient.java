// import required deps

public class ReverseClient {
    public static void main(String[] args) {
        try {
            // init orb
            ORB orb = ORB.init(args, null);
            
            // get nceRef from nameservice
            CORBA.Object nameServiceRef = orb.resolve_initial_references("NameService");
            NamingContextExt nceRef = NamingContextExtHelper.narrow(nameServiceRef);

            String name = "Reverse";
            ReverseImpl revImpl = ReverseHelper.narrow(nceRef.resolve_str(name));

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = br.readLine();
            sysout revImpl.reverse_str(str);
        } catch (Exception e) {

        }
    }
}
