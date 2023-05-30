import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    int id;
    boolean active;

    Process(int id) {
        this.id = id;
        this.active = true;
    }

    void announceCoordinator() {
        System.out.println(this.id + " is elected as cooridnator.");
    }

    void sendElectionMsg(Process process) {
        System.out.println("Election msg received from " + this.id + " by " + process.id);

        if (process.active)
            this.sendResponse(process.id);
    }

    void sendResponse(int receiverId) {
        System.out.println("Process " + receiverId + " replied OK to " + this.id);
    }

    void election(List<Process> processes) {
        int highestId = 0;
        for (Process process: processes) {
            if (process.active) {
                highestId = Math.max(highestId, process.id);
            }
        }

        if (highestId == this.id) {
            this.announceCoordinator();
            return;
        }
        for (Process process: processes) {
            if (this.id < process.id)
                this.sendElectionMsg(process);
        }
    }
}

public class Bully {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++)
            processes.add(new Process(i));

        System.out.println("Enter process to be made inactive: ");
        int dead = sc.nextInt();

        processes.get(dead - 1).active = false;

        for (Process process: processes)
            if (process.active)
                process.election(processes);

        sc.close();
    }
}
