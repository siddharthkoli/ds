import java.util.Scanner;

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of processes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" " + 0);

        int token = 0, choice = 1;
        System.out.println("Token initially at " + token);

        while (choice > 0) {
            System.out.println("Enter sender: ");
            int sender = sc.nextInt();
            System.out.println("Enter receiver: ");
            int receiver = sc.nextInt();
            System.out.println("Enter msg: ");
            int msg = sc.nextInt();

            System.out.print("Token passing: ");
            for (int i = token; i != sender; i = (i + 1) % n) {
                System.out.print(" " + i + " -> ");
            }
            System.out.println(sender);

            System.out.println("Sender " + sender + " is sending data: " + msg);
            for (int i = sender; i != receiver; i = (i + 1) % n) {
                System.out.println("Data " + msg + " forwarded by " + i);
            }

            System.out.println("Receiver " + receiver + " received data " + msg);
            token = receiver;

            System.out.println("Continue? 1/0");
            choice = sc.nextInt();
        }
    }
}