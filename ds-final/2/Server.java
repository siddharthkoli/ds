import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5555);
        System.out.println("Server initiated.");
        Socket s = ss.accept();
        System.out.println("Client connected");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OutputStream ostream = s.getOutputStream();
        PrintWriter pw = new PrintWriter(ostream, true);
        InputStream istream = s.getInputStream();
        BufferedReader receive = new BufferedReader(new InputStreamReader(istream));

        String servermsg = "", clientmsg = "";

        while (true) {
            clientmsg = receive.readLine();
            System.out.println("Client: " + clientmsg);
            if (clientmsg.equals("bye"))
                break;

            System.out.print("Server: ");
            servermsg = br.readLine();
            pw.println(servermsg);

            if (servermsg.equals("bye"))
                break;
        }
        s.close();
        ss.close();
        istream.close();
        ostream.close();

        System.out.println("Connection terminated");
    }
}