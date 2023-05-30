import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 5555);
        System.out.println("Client made connection");

        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        OutputStream ostream = s.getOutputStream();
        PrintWriter pw = new PrintWriter(ostream, true);
        InputStream istream = s.getInputStream();
        BufferedReader receive = new BufferedReader(new InputStreamReader(istream));

        String servermsg = "", clientmsg = "";

        while (true) {
            System.out.print("Client: ");
            clientmsg = br.readLine();
            pw.println(clientmsg);
            if (clientmsg.equals("bye"))
                break;
            
            servermsg = receive.readLine();
            System.out.println("Server: " + servermsg);
            if (servermsg.equals("bye"))
                break;
        }
        s.close();
        istream.close();
        ostream.close();
        System.out.println("Connection terminated.");
    }
}